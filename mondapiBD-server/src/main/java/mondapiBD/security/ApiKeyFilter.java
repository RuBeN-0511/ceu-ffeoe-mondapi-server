package mondapiBD.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Obtenemos el apikey enviada por el cliente en el header
        String requestApiKey = request.getHeader("API-KEY");

        // Comprobamos si es igual a la configurada
        if (apiKey.equals(requestApiKey)) {
            filterChain.doFilter(request, response);
        } else {
            // Si no es correcta, devolvemos un error 401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Petición no autorizada");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String url = request.getRequestURI();
        // Indicamos las URLs que NO deben filtrar (documentación OpenAPI)
        return url.startsWith("/swagger") || url.startsWith("/api-docs");
    }
}
