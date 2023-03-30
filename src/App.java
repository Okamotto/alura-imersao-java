import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexÃ£o HTTP e buscar os top 250 filmes

        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json"; //250 filmes
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"; //Lista menor para fácil manipulação
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair sÃ³ os dados que interessam (titulo, poster, classificaÃ§Ã£o)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir os dados
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao = Double.parseDouble(filme.get("imDbRating"));

            InputStream inputStream = new URL(urlImagem).openStream() ;
            String nomeArquivo =  titulo.replace(":", " -")  + ".png";

            GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
            geradora.criar(inputStream, nomeArquivo, classificacao);

            System.out.println("Título: " + titulo);
            System.out.println("Imagem: " + urlImagem);
            System.out.println("Avaliação: " + classificacao);
            System.out.print("Classificação : ");
            int estrelas = (int) classificacao;
            for (int i = 1; i <= estrelas; i++){
                System.out.print("*");
            }            
            System.out.println();
        }
    }
}