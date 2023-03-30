import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

    public void criar(InputStream inputStream, String nomeArquivo, Double classificacao) throws Exception {

        // leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaALtura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaALtura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        Font fonte = new Font("impact", Font.BOLD + Font.ITALIC, 70);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // seleciona o texto a ser escrito a partir da classificação
        String texto = "";
        if (classificacao >= 7.5 ){
            texto = "Repetacule";
        }else if ((classificacao >= 5.0) && (classificacao <= 7.4)){
            texto = "Matsumoto";
        }else if ((classificacao >= 2.5) && (classificacao <= 4.9)){
            texto = "Meu Deus!";
        }else if((classificacao >= 0) && (classificacao <= 2.4)){
            texto = "Lixooooo";
        }else{
            texto = "Fatal Error";
        }

        //Obter largura do texto e calcular posição X para centralizá-lo
        FontMetrics medidas = graphics.getFontMetrics(fonte);
        int larguraTexto = medidas.stringWidth("TOPZERA");
        int centro = (largura - larguraTexto) / 2;

        // escrever uma frase na nova imagem
        graphics.drawString(texto, centro, novaALtura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }
}