
import api from "@/services/api";

export function searchPlants(query) {
    return api.get(`plants/search`, {
        params: {
            query: query
        }
    })
        .then(resp => resp.data)
        .catch(err => {
            console.error(err);
            return [];
        });
}

export function getAllPlants() {
    return api.get('plants')
        .then(resp => resp.data);
}