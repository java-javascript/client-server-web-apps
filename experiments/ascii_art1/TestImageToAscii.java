import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileWriter;
import javax.imageio.ImageIO;

// javac TestImageToAscii.java
// java -Xmx2g TestImageToAscii  

// images
// http://www.nihilogic.dk/labs/jsascii/
//

// Video
// http://oreilly.com/pub/h/4441

// Text
// http://patorjk.com/software/taag/#p=display&f=Larry%203D&t=TSomething%20else
// https://github.com/patorjk/figlet.js/tree/master/example

class TestImageToAscii{

	public static String toASCII(BufferedImage img, double shrinkFactor)  {
	        BufferedImage image = img;

	        char[] shades = new char[]{'H', '#', 'M', '8', '$', 'O', '=', '+', '*', '|', '-', '^', '~', ';', ',', ':', '.', ' '};
	        StringBuilder output = new StringBuilder();

	        for (double y = 0; y < image.getHeight(); y += shrinkFactor) {
	            for (double x = 0; x < image.getWidth(); x += shrinkFactor) {

	                Color col = new Color(image.getRGB((int) x, (int) y));
	                int avg = (col.getRed() + col.getGreen() + col.getBlue()) / 3;
	                avg = avg / (256 / shades.length - 1);

	                while (avg > shades.length - 1) {
	                    avg--;
	                }
	                output.append(shades[avg] + " " );

	            }
	            output.append("\n");
	        }

	        return output.toString();
	    }

		public static void main(String args[]) throws Exception {

			BufferedImage img = ImageIO.read(new File("photo3.png"));
			Writer output = new BufferedWriter(new FileWriter(new File("ascii.txt")));
			output.append(toASCII(img, 1.5));
			output.close();
			
		}

	
}