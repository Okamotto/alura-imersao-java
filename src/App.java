import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes

        // String url = "https://api.themoviedb.org/3/movie/157336?api_key=cee58b07df41bb12be0ed36e076bf73d";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exiibir os dados
        for (Map<String, String> filme : listaDeFilmes) {
            String title = "Título: ";
            String image = "Imagem: ";
            String rating = "Avaliação: ";
            System.out.println(title + filme.get("title"));
            System.out.println(image + filme.get("image"));
            System.out.println(rating + filme.get("imDbRating"));
            System.out.println();
        }
    }
}