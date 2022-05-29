package br.upe.aula.servlet.rickmorty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/exemplo")
public class RickMortyServlet extends HttpServlet {

    private static final String RICKANDMORTYAPI_URL = "https://rickandmortyapi.com/api/character";

    private RestTemplate clienteHttp;

    @Override
    public void init() throws ServletException {
        this.clienteHttp = new RestTemplate();
        log.info("Cliente HTTP inicializado");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Processamento do verbo GET inicializado");

        try {

            APIRickMortyResponse respostaAPI = this.clienteHttp.getForObject(RICKANDMORTYAPI_URL,
                    APIRickMortyResponse.class);

            if (respostaAPI == null) {
                log.error("Chamada a API não retornou dados");
                req.setAttribute("erro",
                        "ocorreu um erro ao realizar a chamada a API através do método GET: A API não retornou dados");
            } else {
                log.info("Chamada a API ealizada com sucesso, encontrados " + respostaAPI.getInfo().getCount()
                        + " personagens");
                req.setAttribute("personagens", respostaAPI);
            }

            req.getRequestDispatcher("personagens.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error("Ocorreu um erro ao realizar a chamada a API:" + RICKANDMORTYAPI_URL, e);

            req.setAttribute("erro",
                    "ocorreu um erro ao realizar a chamada a API através do método GET: " + e.getMessage().replace("\"",
                            "'"));
        }

        log.info("Processamento do verbo GET inicializado");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Processamento do verbo POST inicializado");

        String respostaServlet = null;

        try {

            ResponseEntity<String> respostaAPI = this.clienteHttp.getForEntity(RICKANDMORTYAPI_URL, String.class);

            if (respostaAPI.hasBody()) {

                respostaServlet = respostaAPI.getBody();
                log.info("Chamada a API ealizada com sucesso");
                resp.setStatus(HttpStatus.OK.value());

            } else {
                log.error(
                        "Cahamada a API realizada com inconsistência código HTTP:" + respostaAPI.getStatusCodeValue());

                resp.setStatus(respostaAPI.getStatusCodeValue());

                respostaServlet = "{" +
                        " \"erro\": " + respostaAPI.getStatusCodeValue() + "," +
                        " \"mensagem\": \"A API não retornou dados\"" +
                        "}";
            }

        } catch (Exception e) {
            log.error("Ocorreu um erro ao realizar a chamada a API:" + RICKANDMORTYAPI_URL, e);

            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            respostaServlet = "{" +
                    " \"erro\": " + HttpStatus.INTERNAL_SERVER_ERROR.value() + "," +
                    " \"mensagem\": \"" + e.getMessage().replace("\"", "'") + "\"" +
                    "}";
        }

        resp.setContentType("application/json");
        resp.getOutputStream().print(respostaServlet);

        log.info("Processamento do verbo POST inicializado");
    }

}
