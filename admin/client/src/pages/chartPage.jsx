import React, { Component } from 'react'
import { Doughnut } from 'react-chartjs-2';
import api from '../api'


const expData = {
    labels: ['원장님', '선생님', '학부모'],
    datasets: [
        {
            labels: ['원장님', '선생님', '학부모'],
            data: [0, 0, 0],
            borderWidth: 2,
            hoverBorderWidth: 3,
            backgroundColor: [
                "red",
                "green",
                "blue"
            ],
            fill: true
        }
    ]
}



class chartPage extends Component {

    constructor(props) {
        super(props)
        this.state = {
            member: [],
        }
    }
    async memberList() {
        const response = await api.adminSelect();
        this.setState({
            member: response.data,
        })
        for (var i = 0; i < this.state.member.length; i++) {
            if (this.state.member[i].MEMBER_ROLE === 1) {
                expData.datasets[0].data[0]++;
            } else if (this.state.member[i].MEMBER_ROLE === 2) {
                expData.datasets[0].data[1]++;
            } else if (this.state.member[i].MEMBER_ROLE === 3) {
                expData.datasets[0].data[2]++;
            }
        }
    }

    componentDidMount() {
        this.memberList();
    }

    render() {
        return (
            <Doughnut
                options={{
                    legend: {
                        display: true,
                        position: "right"
                    }
                }}
                data={expData}
                height={120}
            />
        )
    }
}

export default chartPage