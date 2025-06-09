import { config } from '@/config'

export function getImageUrl(path) {
    return `${config.IMAGE_BASE_URL}${path}`
}