import { Restaurant } from "./restaurant.js";

export class Repository {
    constructor() {
        this.restaurants = [
            new Restaurant("mama manu", "piezisa", "10"),
            new Restaurant("kfc", "mall", "2"),
            new Restaurant("conacul verde", "bn", "2")
        ];
    }
};