# Vipps for Streamlabs

Hello, my name is Asbjørn Riddervold and I’m a IT student that work at Vipps AS in Norway. Vipps is a mobile payment solution which is used by 3.3 million Norwegians (we are currently 5.2 million Norwegians living in Norway). With Vipps, you can “vippse” your friend 100 Norwegian kroner for the dinner he paid for (person to person, “P2P”) or split the bill easily among your travel companions. You can also pay with Vipps when buying a new microwave on the internet (business to consumer, B2C).


In March 2019 Vipps arranged a two-day internal hackathon where all employees could participate. There were several categories, and in true hackathon spirit, imagination was the limit.


First day of the hackathon, we formed teams and shared our ideas. My team consisted of me, Henrik Wille and Håkon Strøm Lie. Some months before we had discussed different approaches on how we could manage to get Vipps present on Twitch. Twitch is a live streaming platform, mostly known for streaming of games. The streamers can receive donations from the viewers and many streamers have created events that will trigger when a viewer donates a certain amount. Twitch do have their own internal currency, “bits”. You can buy bits and use them to e.g. donate to a streamer. But with bits, Twitch takes as much as 29% which makes it more expensive to donate and less is going to the streamers. And by now, Vipps is only available for Norwegian citizens, which doesn’t make it easy to partner up with Twitch.


Which takes me to Streamlabs. Streamlabs is a video broadcasting platform that streamers can use to broadcast live video to Twitch, YouTube and more. It also offers an very nice API which can be used to trigger events in the stream based on events that the streamer itself activate. For example here is the endpoint used for donations:

This is good news for us. Since we want to make something that can be used with Twitch, and Streamlabs is used by the majority of the people who are streaming on Twitch, we were able to make what we have been talking about the past months: Make Vipps available as a payment option on Twitch.


But why do we want to make Vipps available as a payment option on Twitch ?
Norwegians’ love Vipps! It is the de-facto standard for P2P payments. If Vipps is a present payment option, Norwegians choose it pretty much all of the time. Remember, I work in Vipps, so read this statement with “a pinch of salt”


## Okay so let’s begin!
We decided to just focus on the key features in our solution. Just the minimal to get something up and go. So, for example refunds were not a part of the end solution. What needed to do was:

* Create a backend that is integrated with Vipps’ API for B2C/eCom payments. This includes payments initiation and listening for callbacks from successful/unsuccessful payments attempts. The backend also had to communicate with Streamlabs. Streamlabs has an open API that we used. We used the specific endpoint: /donation

* Create a Vipps “donate” button that the streamer could place in their channel description or anywhere else. This button would trigger a Vipps payment(initiate payment) and open a landing page where the viewer fills in a donation amount, a text that is showed and optionally a GIF of choice.
* Create a frontend where the viewer could enter amount to donate, message to be displayed and name to be showed as sender. This data is then submitted to the back end which replies with a redirect URL to the Vipps landing page.


We hosted both the backend and frontend on Heroku. Heroku was a pleasant experience for hosting. Both easy and cheap (free).


And, surprisingly enough, everything worked perfectly during the live-demo. The audience donated cash to Håkon, through Vipps, while playing the addictive game, slither.io. Yey!
