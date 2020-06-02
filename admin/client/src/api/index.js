import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:3001/admin'
})

const adminSelect = () => api.get('/selectList')
const memberUpdate = () => MEMBER_SEQ => api.update('/memberUpdate' + MEMBER_SEQ)

const apis = {
    adminSelect,
    memberUpdate,
}

export default apis