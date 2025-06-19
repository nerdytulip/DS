package com.company.Stack;

import java.util.ArrayList;
import java.util.List;

class Car {
    int position;
    double timeToTarget;

    Car(int position, int speed, int target) {
        this.position = position;
        this.timeToTarget = (double)(target - position) / speed;
    }
}

public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cars.add(new Car(position[i], speed[i], target));
        }

        // Sort cars by position in descending order
        cars.sort((a, b) -> b.position - a.position);

        int fleets = 0;
        double lastFleetTime = 0;

        for (Car car : cars) {
            // If the car takes more time than the last fleet’s arrival time,
            // it cannot catch up → forms a new fleet
            if (car.timeToTarget > lastFleetTime) {
                fleets++;
                lastFleetTime = car.timeToTarget; // update the most recent fleet's time
            }
            // If timeToTarget <= lastFleetTime → the car joins the fleet ahead
        }

        return fleets;
    }
}
