import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:3001/admin'
})

const adminSelect = () => api.get('/selectList')

const apis = {
    adminSelect,
}

export default apis