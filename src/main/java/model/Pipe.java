package model;

import math.Calculation;

import java.util.ArrayList;
import java.util.List;

public class Pipe {
    private final double length;
    private final double diameter;
    private final int horizontalQuantity;
    private final int verticalQuantity;
    private final double deltaDiameter;
    private final double deltaLength;

    private List<List<Point>> points;

    public Pipe(double length, double diameter, int horizontalQuantity, int verticalQuantity) {
        this.length = length;
        this.diameter = diameter;
        this.horizontalQuantity = horizontalQuantity;
        this.verticalQuantity = verticalQuantity;
        this.deltaDiameter = diameter / (verticalQuantity - 1);
        this.deltaLength = length / (horizontalQuantity - 1);
        initializePoints();
    }

    public List<List<Point>> getPoints() {
        return points;
    }

    private void initializePoints() {
        points = new ArrayList<>();
        Point startPoint = new Point(0, 0, 0, 0, 0, false);
        for (int i = 0; i < verticalQuantity; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < horizontalQuantity; j++) {
                if (i > 0 && j > 0) {
                    row.add(startPoint);
                } else {
                    row.add(new Point(0, 0, 0, 0, 0, false));
                }
            }
            points.add(row);
        }
    }
    public void calculatePoints(List<Point> startVerticalPoints) {
        Point verticalMainPoint = startVerticalPoints.get(verticalQuantity / 2);
        Calculation calc = new Calculation();
        System.out.println(points.size());
        for (int i = 0; i < verticalQuantity; i++) {
            points.get(i).set(0, startVerticalPoints.get(i));
            for (int j = 1; j < horizontalQuantity; j++) {
                double x = j * deltaLength;
                double y = i * deltaDiameter;
                Point prevPoint = points.get(i).get(j - 1);
                double velocity = calc.calcVelocity(verticalMainPoint.velocity, diameter, y, deltaDiameter);
                double pressure = calc.calcPressure(prevPoint.pressure, prevPoint.velocity, diameter, deltaLength);
                double temp = calc.calcTemp(prevPoint.temperature, deltaLength, diameter, velocity);
                double flow = calc.calcFlow(prevPoint.velocity, prevPoint.density);
                Point updatedPoint = new Point(velocity, temp, pressure, flow, startVerticalPoints.get(0).density, calc.isBorderPoint(i, startVerticalPoints.size()));
                points.get(i).set(j, updatedPoint);
            }
        }
    }

    public void outMatrix() {
        for (int i = 0; i < verticalQuantity; i++) {
            for (int j = 0; j < horizontalQuantity; j++) {
                System.out.print(points.get(i).get(j).velocity + " ");
            }
            System.out.println(); // Переход на новую строку для следующей строки матрицы
        }
    }
}
/*
    public void Destiny_with_Burst (double length_b) {

        for (int i = 0; i < horizontalQuantity; i++) {
            for (int j = 1; j < verticalQuantity; j++) {
                double x = j * deltaLength;
                double y = i * deltaDiameter;
                //double density0 = points.get(0).get(0).density; правильная строчка
                double density0 = 10;
                double density1 = density0*0.6;
                double density_step = 0;
                if ((x < -0.5 * Math.pow(y-diameter,2) + length_b)) {
                    //установить поинту начальную плотность
                    points.get(i).get(j).density = density0;
                }else {
                        if (points.get(i).get(j-1).density == density0){
                            double point_c = length_b/deltaLength-j;
                            density_step = (density0-density1)/point_c;
                        }
                        points.get(i).get(j).density = points.get(i).get(j-1).density - density_step;
                    }



            }

        }
    }



 */