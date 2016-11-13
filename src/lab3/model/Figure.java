package lab3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Created by script972 on 08.10.2015.
 */

public class Figure {
    private double [] x={30,30,50,50, 130,210,210,230,230,210,130,50,30};
    private double [] y={30, 350, 350, 50, 150, 50, 350, 350, 30, 30, 130, 30, 30 };
    private double [] x1={300, 300, 330, 380, 410, 410, 385, 440, 440, 400, 315, 290, 290, 275, 275, 310, 400, 455, 455, 405, 425, 425, 380, 330, 285, 285};
    private double [] y1={75,   50,  30,  30,  50, 120, 150, 190, 310, 335, 335, 300, 275, 275, 305, 352, 352, 318, 180, 145, 120,  45,  15,  15,  45, 75 };





    private int[][] polygons = {
            {0,1,2,3,4,5,6,7,8,9,10,11,12},
            {0,1,2,3,4,5,6,7,8,9,10,11,12}
    };
    private int[][] polygons1 = {
            {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25},
            {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}
    };


    private double[][] matrix = {
            {1,0,0}, {0,1,0}, {0,0,1}
    };

    private double fx(double x, double y) {
        return matrix[0][0] * x + matrix[0][1] * y + matrix[0][2];
    }

    private double fy(double x, double y) {
        return matrix[1][0] * x + matrix[1][1] * y + matrix[1][2];
    }

    public void draw(GraphicsContext gc, Color color) {
        PixelWriter pixelWriter = gc.getPixelWriter();
        pixelWriter.setColor(120,120, Color.RED);
        pixelWriter.setColor(121,120, Color.RED);
        gc.setStroke(color);
        for (int[] polygon : polygons) {
            double[] xe = new double[polygon.length];
            double[] ye = new double[polygon.length];
            for (int j = 0; j < xe.length; j++) {
                xe[j] = fx(x[polygon[j]], y[polygon[j]]);
                ye[j] = fy(x[polygon[j]], y[polygon[j]]);
            }


            gc.strokePolygon(xe, ye, xe.length);
        }



        for (int[] polygon : polygons1) {
            double[] xe = new double[polygon.length];
            double[] ye = new double[polygon.length];
            for (int j = 0; j < xe.length; j++) {
                xe[j] = fx(x1[polygon[j]], y1[polygon[j]]);
                ye[j] = fy(x1[polygon[j]], y1[polygon[j]]);
            }
            gc.strokePolygon(xe, ye, xe.length);
        }
    }

    public void rotate(double alpha) {
        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double[][] r = {
                {cos, -sin, 0},{sin, cos, 0},{0,0,1}
        };

        matrix = multMatrix(r,matrix);
    }

    public void move(double dx, double dy) {
        double[][] t = {
                {1,0,dx},{0,1,dy},{0,0,1}
        };
        matrix = multMatrix(t,matrix);
    }

    public void scale(double v) {
        double[][] t = {
                {v,0,0},{0,v,0},{0,0,1}
        };
        matrix = multMatrix(t,matrix);
    }











    private double[][] multMatrix(double[][] r, double[][] matrix) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += r[i][k] * matrix[k][j];
                }
            }
        }
        return result;
    }
}
