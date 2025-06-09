import api from "@/services/api";

export function getCollections() {
    return api.get('collections')
        .then(resp => resp.data);
}

export function getUserCollections() {
    return api.get('collections/user')
        .then(resp => resp.data);
}

export function createCollection(collection) {
    return api.post('collections', collection, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(resp => resp.data)
        .catch(err => {
            console.error(err);
            return err.response;
        });
}

export async function getCollectionById(id)  {
    try {
        const result = await api.get(`collections/${id}`);
        return result.data;
    } catch (err) {
        if (err.response.status === 404) {
            throw new Error("Collection not found or access denied");
        }
        throw new Error("Failed to fetch collection")
    }
}