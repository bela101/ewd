import api from "@/services/api";

export function getCollections() {
    return api.get('collections')
        .then(resp => resp.data);
}