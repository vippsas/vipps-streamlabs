import React from "react";


const initialState = {
    correctAnswers: 0,
    questions: null,
    currentQuestion: 0,
    quizDone: false,
    loaded: false
};

export class FallBack extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {

        return(
            <article>
                <div className="grid-container">
                            <img className="bannerFallback" src="../../img/Banner2.png" alt="lol"/>

                </div>
                <div className="grid-container">
                    <div className="grid-x grid-padding-x grid-margin-x  grid-padding-y grid-margin-y content-block">

                        <div className="cell small-12 medium-12 text-center align-self-middle">
                            <img className="" src="../../img/Confirmed3.png" alt="lol"/>
                                <div className="rich-text t">
                                    <br />
                                        <h2>Ferdig Vippset! Kom deg tilbake i streamen;)</h2>
                                </div>
                        </div>
                    </div>
                </div>
            </article>
    );
    }
}