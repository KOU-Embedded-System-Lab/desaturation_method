import java.awt.image.BufferedImage;

/**
 * Created by asus / Irma Dibra on 01-Aug-17.
 *
 */
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO ;
import javax.imageio.stream.ImageInputStream;

public class desaturation_gray {

    public static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        //  System.out.println("color2rgb "+newPixel);
        return newPixel;

    }
    public static int Min_find(int[] p){

        int min = p[0];
        for (int i = 0 ; i<p.length;i++){
            if (p[i]<min)
                min = p[i];
        }
        return min ;
    }
    public static int Max_find (int[] p ){
        int max = p [0];
        for (int i = 0 ; i <p.length ;i++){
            if (p[i ] > max )
                max = p[i];
        }
        return max ;
    }
    public static void main (String []args){
        int alpha , red , green , blue ;
        int newPixel ;
        int height , width ;
        int []p = new int [3];

        BufferedImage img = null ;
        File f = null ;
        //read image
        try {
            f= new File("D:\\Image\\Taj.jpg");
            img = ImageIO.read(f);
        }catch (IOException e ){
            System.out.println("Error"+e);
        }
        int [] desortion = new int[511];
        for (int i = 0 ; i<511 ;i++) {
            desortion[i] = i / 2;
        }

        for (int i = 0 ; i < img.getWidth() ; i++){
            for (int j = 0 ; j < img.getHeight(); j++){
                // get pixels from the image
                alpha = new Color (img.getRGB(i , j )).getAlpha();
                red = new Color (img.getRGB(i , j )).getRed();
                green = new Color (img.getRGB(i , j )).getGreen();
                blue = new Color (img.getRGB(i , j )).getBlue();

                p[0] = red;
                p[1]= green ;
                p[2]= blue ;
                // formula for disortion
                int val_new = (int )(Max_find(p)+ Min_find(p));

                val_new =desortion[val_new];
                //set the newpixels in
                newPixel = colorToRGB(alpha ,val_new , val_new , val_new);
                img.setRGB(i , j , newPixel);

            }
        }
        //write  image
        try {
            f= new File( "D:\\Image\\yes.jpg");
            ImageIO.write(img , "jpg" , f );
        }catch (IOException e){
            System.out.println("Error " +e);
        }




    }

}
