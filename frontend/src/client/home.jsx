import React from "react";

export class Home extends React.Component {


    // Set server url to backend!
    SERVER_URL = "HTTPS://YOURSERVER.COM";

    constructor(props) {
        super(props);
        this.state = ({
            streamer: this.props.match.params.streamer,
            waiting: false,
            deeplink: null,
            donationAmount: 0,
            donationFrom: null,
            donationMessage: null
        });
    }
    onFormSubmit = (event) => {
        /*
            Prevent the default action of submitting a form,
            which would be a POST request to the server we got
            the HTML from.
            But here we rather want to do AJAX to an external
            web service.
         */

        console.log(this.state);

        event.preventDefault();

        this.fetchDeepLink().then( responseURL => {
                this.setState({deeplink: responseURL.url, waiting: true});
            }
        );
    };
    renderRedirect = () => {
        if (this.state.waiting) {
            console.log(this.state.deeplink);
            window.location = this.state.deeplink;
        }
        else {
            console.log('Waiting state = ' + this.state.waiting);
        }
    };
    onNameChange = (event) => {
        this.setState({donationFrom: event.target.value});
    };
    onAmountChange = (event) => {
        this.setState({donationAmount: event.target.value});
    };
    onTextChange = (event) => {
        this.setState({donationMessage: event.target.value});
    };


    fetchDeepLink = async () => {
        if(this.SERVER_URL === "") console.log("No server URL set in client/home.jsx");
        else {
            let data = JSON.stringify({
                "senderName": this.state.donationFrom,
                "amount": parseInt(this.state.donationAmount),
                "transactionText": this.state.donationMessage
            });

            return await fetch(this.SERVER_URL + '/initiatePayment', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "cache-control": "no-cache",
                    // "Content-Type": "application/x-www-form-urlencoded",
                },
                body: data, // body data type must match "Content-Type" header
            }).then(response => response.json());

        }};


    render() {
        return (
            <div className="body">
                {!this.state.waiting &&
                <div className="frontNotWaiting">
                    <main>
                        <article>
                            <form onSubmit={this.onFormSubmit}>
                                <div className="grid-container">
                                    <div className="grid-x grid-padding-x grid-margin-x margin-top-s">
                                        <div className="cell small-12 medium-12 text-center align-self-middle">
                                            <img alt="bannerVipps" src="./img/Banner2.png" />
                                        </div>
                                    </div>
                                </div>
                                <div className="grid-container">
                                    <div className="rich-text"><h2>Hva heter du?</h2>
                                    </div>
                                    <div className="cell">
                                        <input type="text" placeholder="navn" onChange={this.onNameChange} />
                                    </div>
                                    <div className="rich-text"><h2>Hvor mye vil du donere?</h2>
                                    </div>
                                    <div className="cell">
                                        <input type="text"placeholder="...kr" onChange={this.onAmountChange} />
                                    </div>
                                    <div className="cell">
                                        <h2>Hva vil du si?</h2>
                                        <input type="text" placeholder="Si noe trivelig!" onChange={this.onTextChange} />
                                    </div>
                                    <input type="submit" className="button primary" value="Send" />
                                </div>
                            </form>
                        </article>

                    </main>
                </div>
                }
                {this.state.waiting &&
                <div className="frontWaiting">
                    redirecting...
                    {this.renderRedirect()}
                </div>
                }

            </div>
        )
    }
}