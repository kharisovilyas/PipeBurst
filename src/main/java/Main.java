import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double generationSyntacticVelocity = new Random().nextDouble() * 1000;

        Point generationSyntacticData = new Point(
                1.5,
                0,
                500_000,
                90,
                1000,
                false
        );

        List<Point> listPoints = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listPoints.add(generationSyntacticData);
        }
        Pipe pipe = new Pipe(
                500000,
                50,
                1000,
                5
        );

        pipe.calculatePoints(listPoints);
        List<String> matrixHeaders = List.of("Velocity", "Flow", "Pressure", "Temperature", "Density");
        List<String> matrixData = new ArrayList<>();
        pipe.Destiny_with_Burst(10_000);

        for (String header : matrixHeaders) {
            StringBuilder data = new StringBuilder();
            for (List<Point> row : pipe.getPoints()) {
                for (Point point : row) {
                    String value = "";
                    switch (header) {
                        case "Velocity":
                            value = String.valueOf(point.velocity);
                            break;
                        case "Flow":
                            value = String.valueOf(point.flow);
                            break;
                        case "Pressure":
                            value = String.valueOf(point.pressure);
                            break;
                        case "Temperature":
                            value = String.valueOf(point.temperature);
                            break;
                        case "Density":
                            value = String.valueOf(point.density);
                            break;
                    }
                    data.append(value).append("\t");
                }
                data.append("\n");
            }
            matrixData.add(data.toString());
        }

        for (int i = 0; i < matrixHeaders.size(); i++) {
            String header = matrixHeaders.get(i);
            String data = matrixData.get(i);
            System.out.println("-----------" + header + "-----------");
            System.out.println(data);
            System.out.println();
        }



    }
}

