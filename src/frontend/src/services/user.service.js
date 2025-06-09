import api from "@/services/api";

export function getUserId() {
    return api.get('user/current/id')
        .then(resp => resp.data);
}

export function getUsername() {
    return api.get('user/current/username')
        .then(resp => resp.data);
}