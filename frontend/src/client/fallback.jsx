import React from "react";
export class Fallback extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({
            orderId: this.props.match.params.orderId,
            status: undefined,
            intervalId: undefined,
            errorMessage: undefined
        });
    }
    componentWillMount() {
        let intervalId = setInterval(this.getOrderStatus, 3000);
        this.setState({intervalId: intervalId});
    }

    getOrderStatus = async () => {
        return await fetch('/api/donations/' + this.state.orderId, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(response => response.json())
            .catch(error =>
                this.setState({status: 'ERROR', errorMessage: error})
            )
            .then(responseJson => {
                if(responseJson.orderStatus !== 'PENDING') {
                    clearInterval(this.state.intervalId);
                }
                this.setState({status: responseJson.orderStatus})
            })

    };

    renderStatus = () => {
        switch(this.state.status) {
            case 'SUCCESS':
                return (
                    <img className="statusImage" src="../../img/success.png" alt="success"/>
                );
            case 'CANCELLED':
                return (

                    <img className="statusImage" src="../../img/cancelled.png" alt="cancelled"/>
                );
            case 'FAILED':
                return (
                    <img className="statusImage" src="../../img/failed.png" alt="failed"/>
                );
            case 'PENDING':
                return (
                    <img className="statusImage" src="../../img/pending.png" alt="pending"/>
                );
            case 'ERROR':
                return(
                    <div>
                        <img className="statusImage" src="../../img/failed.png" alt="failed"/>
                        Feilmelding: {this.state.errorMessage}
                    </div>
                );
            default:
                return (
                    <div>
                    <img className="statusImage" src="../../img/pending.png" alt="pending"/>
                    </div>
                )
        }
    };

    render() {
        return (
        <div className="fallbackInfo">
            <div className="grid-container">
                <div className="grid-x grid-padding-x grid-margin-x margin-top-s">
                    <div className="cell small-12 medium-12 text-center align-self-middle">
                        <img className="profile-image-fallback" alt="bannerVipps" src="../../img/stream-profile-picture.png"/>
                    </div>
                </div>
            </div>

            <div className="bannerHeaderVipps">Takk for at du støtter Emzia!</div>
            {this.renderStatus()}
        </div>
        )
    }
}