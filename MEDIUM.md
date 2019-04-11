##Vipps for Streamlabs

Hello, my name is Asbjørn Riddervold and I’m a student that work at Vipps AS in Norway. 
Vipps is a mobile payment solution which is used by 3.3 million Norwegians (we are currently 5.2 million Norwegians living in Norway). 
With Vipps, you can “vippse” your friend 100 Norwegian kroner for the dinner he paid for (Person 2 Person) or split the 
bill easily among your travel companions. You can also pay with Vipps when buying a new microwave on the internet (Business To Consumer).  

Last week Vipps arranged a two-day internal hackathon where all employees 
could participate. There were several categories, and in true hackathon spirit; imagination was the limit. 

Wednesday morning, we formed teams and shared our ideas. My team had earlier discussed how Norwegian streamers 
had accepted donations through Vipps. The streamers had used P2P to receive donations by showing their 
private phone number in the stream. Which for several reasons is not an optimal for several reasons: 

P2P is initiated by sending to the streamers private phone number, which mean that the streamers 
had to expose their private phone number to receive donations. 

P2P doesn’t have an open API, which mean that donation done cannot 
trigger donation events on Twitch through Streamlabs. This was solved by the streamer opening 
the Vipps app and manually read the donation and text to the viewers. Not optimal for playing games. 

So why do some of the Norwegian Twitch streamers open for receiving donations through Vipps even though 
PayPal already is present on Twitch? 

Norwegian’s love Vipps! <Insert numbers from surveys won>. If Vipps is a present payment option, 
Norwegians choose it <insert percentage here> of the time. 

Anyway, my team quite early agreed on creating an integration between Vipps and Streamlabs. We wanted 
to make it possible to donate money to norwegian streamers and to trigger events in the stream by the end of the Hackathon. 
Exciting, but also a lot of research was a head of us.  

We decided to just focus on the key features in our solution. Just the minimal to get something up and go. So, 
for example refunds were not a part of the end solution. What needed to do was: 

create a back end that is integrated with Vipps’ API for B2C/eCom payments. This include initiate payments and 
listening for callbacks from successful/unsuccessful payments attempts. The back end also had to communicate with Streamlabs 
(www.streamlabs.com). Streamlabs is a streaming software that allows you to broadcast your video to Twitch, but also other services. 
Streamlabs has an open API that we used. We used the specific endpoint: /donation 

create a Vipps-“donate”-button that the streamer could place in their channel description or anywhere else. This button would 
trigger a Vipps payment(initiate payment) and open a landing page where the viewer fill in donation amount, text that is showed and optionally a GIF of choice. 