import React from "react";


export class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = ({
            waiting: false,
            deeplink: null,
            donationAmountEventInteger: 0,
            donationAmountInNOK: 0,
            donationAmountInUSD: 0,
            usdToNokRate: 9.00,
            donationFrom: null,
            donationMessage: null,
            nameInfoVisible: false,
            donationInfoVisible: false,
            formIsValid: true
        });
    }

    componentWillMount() {
        fetch('/api/exhangerate', {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => response.json())
            .then(data => {
                this.calculateExchangeRate(data)
            });
    }

    calculateExchangeRate = (body) => {
        const usd = body.rates.USD
        const nok = body.rates.NOK

        const usdToNokRate = nok / usd

        this.setState({
            usdToNokRate: usdToNokRate
        })
    }

    onFormSubmit = (event) => {
        event.preventDefault();
        if (!this.state.donationAmountInNOK || !this.state.donationAmountInNOK || !this.state.donationFrom || !this.state.donationMessage) {
            this.setState({formIsValid: false})
        } else {
            this.fetchDeepLink().then(responseWithURL => {
                    if (!responseWithURL.url) {


                        return;
                    }
                    this.setState({deeplink: responseWithURL.url, waiting: true});
                }
            );
        }
    };
    renderRedirect = () => {
        if (this.state.waiting) {
            window.location = this.state.deeplink;
        } else {

        }
    };

    onNameChange = (event) => {
        if (new RegExp("^[a-zA-Z0-9_]*$").test(event.target.value)) {
            this.setState(
                {donationFrom: event.target.value,
                    formIsValid: true
                });
        } else {
            this.setState({donationFrom: undefined})
        }
    };

    onAmountInUSDChange = (event) => {
        this.setState({donationAmountEventInteger: this.state.donationAmountEventInteger + 1});

        let parsed = parseFloat(event.target.value.replace(/,/g, '.'))

        if (isNaN(parsed) || parsed < 2)
            this.setState(
                {
                    donationAmountInUSD: undefined,
                    donationAmountInNOK: 0
                });
        else {
            this.setState(
                {
                    donationAmountInUSD: event.target.value.replace(/,/g, '.'),
                    donationAmountInNOK: (parsed * this.state.usdToNokRate).toFixed(2),
                    formIsValid: true
                });

        }
    };
    onTextChange = (event) => {
        if (event.target.value.length >= 229) {
            this.setState({donationMessage: undefined})
        } else {
            this.setState({
                donationMessage: event.target.value,
                formIsValid: true
            });
        }
    };

    fetchDeepLink = async () => {
        let data = JSON.stringify({
            senderName: this.state.donationFrom,
            amountInNOK: this.state.donationAmountInNOK.split('.').join(""),
            amountInUSD: this.state.donationAmountInUSD,
            transactionText: this.state.donationMessage
        });

        return await fetch('/api/donations', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: data,
        }).then(response => response.json());
    };

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
                                            <img className="profile-image" alt="bannerVipps"
                                                 src="./img/stream-profile-picture.png"/>
                                            <div className="bannerHeaderVipps">Donér til Emzia</div>
                                        </div>
                                    </div>
                                </div>


                                <div className="grid-container">

                                    <div className={"amount-div"}>
                                        <div className={"amount-div-amount-concat-with-usd-suffix"}>
                                            <input className={"amount-input-usd"} type="text" placeholder="0"
                                                   onChange={this.onAmountInUSDChange}/>
                                            <p className={"amount-paragraph-usd-suffix"}>USD</p>
                                        </div>

                                        <p className={"amount-paragraph-in-kroner"}>Tilsvarer {this.state.donationAmountInNOK} kr</p>
                                        {((this.state.donationAmountInUSD === undefined || this.state.donationAmountInUSD < 2) && this.state.donationAmountEventInteger > 0) ?
                                            <p className="error-message-minimum-amount-visible">
                                                Minimum beløp er 2 USD
                                            </p> :
                                            <p className="error-message-minimum-amount-not-visible">
                                                Minimum beløp er 2 USD
                                            </p>
                                        }

                                    </div>

                                    <div className="message-div">
                                        <textarea className={"message-input"} cols="40" rows={"2"} placeholder="Melding" onChange={this.onTextChange}/>
                                        {this.state.donationMessage === undefined ?
                                            <div className="error-message-message-visible">
                                                Kan ikke være større enn 230 tegn
                                            </div>
                                            :
                                            <p className="error-message-message-not-visible">
                                                Kan ikke være større enn 230 tegn
                                            </p>}
                                    </div>

                                    <div className="name-div">
                                        <input className={"name-input"} type="text" placeholder="Navn" onChange={this.onNameChange}/>
                                        {this.state.donationFrom === undefined ?
                                            <div className="error-message-name-visible">
                                                Støtter kun A-Z
                                            </div>
                                            :
                                            <p className="error-message-name-not-visible">
                                                Støtter kun A-Z
                                            </p>
                                        }
                                    </div>

                                    <div className={"submit-div"}>
                                        <input type="submit" className="button-submit" value="Donér med Vipps"/>
                                        {(!this.state.formIsValid ?
                                            <div className="error-message-name-visible">
                                                Et eller flere av feltene har ugyldig data
                                            </div>
                                            :
                                            <p className="error-message-name-not-visible">

                                            </p>)
                                        }
                                    </div>
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