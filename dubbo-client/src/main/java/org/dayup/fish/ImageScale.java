package org.dayup.fish;

import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageScale {

    public static void main(String[] args) throws IOException {
        System.out.println("开始缩放图标");
        scale("D:\\壁纸\\图标\\80个创意评论PNG图标", 30, 30);
        System.out.println("结束缩放图标");
    }


    private static void scale(String path, int width, int height) throws IOException {
        File imgDir = new File(path);
        if (!imgDir.exists() || !imgDir.isDirectory()) {
            throw new IllegalArgumentException("path is not directory");
        }
        File smDir = new File(path + File.separator + "scale");
        System.out.println("缩放文件目录:" + smDir.getPath());
        if (!smDir.exists()) {
            smDir.mkdir();
        }
        File scaleImg;
        for (File img : imgDir.listFiles(file -> {
            if (file.getName().contains(".png")) {
                return true;
            }
            return false;
        })) {
            BufferedImage apples = ImageIO.read(img);
            scaleImg = new File(smDir.getPath() + File.separator + img.getName().toLowerCase());
            if (!scaleImg.exists()) {
                scaleImg.createNewFile();
            }
            ResampleOp resampleOp = new ResampleOp(DimensionConstrain.createMaxDimension(width, height, true));
            BufferedImage rescaled = resampleOp.filter(apples, null);
            ImageIO.write(rescaled, "png", scaleImg);
        }

    }
}
