Hello Team,

This document will provide the step by step approach and challenges of this assignment I've gone through. And I'll try my best to explain my approach to finish this assignment.

I've completed this assignment in 4 major stages.

STAGE - 1 :-- Preparing models and mapping required parameters. {Used POST-MAN, Serializable data model}

==> At first, I've ran the API's which were provided in the document with the help of POST-MAN and come up with the required parameters and their data serialized names.
==> With the help of these two API's , I've generated two models for Anime List and for selected Anime Details.

STAGE - 2 :-- Preparing User Interface and binding the views with view binding approach. (Used Linear, Recycler, Scroll and WEB-PREVIEW)

==> By using Linear Layouts and Recycler Views, I've created an interface which holds Anime list in one screen with a view card and Anime details screen with WEB-PREVIEW approach to load and play the trailer.
==> With the help of adapter, I've connected the recycler view with the anime item card view by injecting response data of API's.

STAGE 3 :-- Preparing API's call requests. (Used Retrofit with HTTP protocol)

==> By building a base-URL with the help of retrofit and initializing call requests by pointing to relevant view models, I've completed the API call request implementation.
==> To work with network and stream videos in mobile , we need to pass network requests to AndroidManifest.

STAGE 4 :-- Research and Implementation of suitable library to play URL's in Anime Details Screen.  (Tried exoplayer, music3.exoplayer approach and Used Web-Preview approach).

==> In my past application development experience, we used exoplayer libraries to play media and some online data, Due to library deprication, I've gone through some research on which version would be best 
    for any type of URL, Atlast I've settled with base method WEB-PREVIEW which is quite effective approach to play trailers.
==> By this the provided assignment is completed successfully.


Additional Add-Ons :--

==> I've seen availability of pagination for Anime List, In mean time, I've implemented simple scroll-down pagination approach.
==> Minimized exceptions with Null-Safety and Default values in worst case scenario.


This is how I've completed the given assignment.
I hope you team will be satisfied with my efforts and I'll wait for positive response.
