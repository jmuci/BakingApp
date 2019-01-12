# BakingApp
Assignment form the Android Nanodegree from Udacity


## App Description
The assignment is to create a Android Baking App that will allow Udacity’s resident baker-in-chief, Miriam, to share her recipes with the world. You will create an app that will allow a user to select a recipe and see video-guided steps for how to complete it.

[The recipe listing is located here][link].

  [link]: http://go.udacity.com/android-baking-app-json


The JSON file contains the recipes' instructions, ingredients, videos and images you will need to complete this project. Don’t assume that all steps of the recipe have a video. Some may have a video, an image, or no visual media at all.

One of the skills you will demonstrate in this project is how to handle unexpected input in your data -- professional developers often cannot expect polished JSON data when building an app.

## TODOs

* [X] Set up Recycler view for MainFragment with Card Views <br>
* [X] Setup Dagger 
* [X] Design and implement View Model Skeleton with Repository Patten
* [X] Implement model of Json objects (Recipe, Steps, Ingredients)
* [X] Implement Loading Animation (ProgresBar)
* [X] Implement Json fetching with Retrofit as part of Repository
* [X] Support fallback case if Network request fails to deliver the recipes (caching?)
* [X] Show error if Data Loading fails
* [X] Implement Pull to Refresh
* [X] Clicking on a card opens a new Fragment
* [X] Implement list of steps according to mock up, as a Recycler View List
* [X] Clicking on Steps, opens detailed step View which will hold video.
* [X] Implement View Playback of the Recipes using Exo Player
* [X] Map images to each recipe by ID.
* [X] Restore playback state on rotation and add full screen.
* [X] Make ingredients card clickable and show ingredients list in a fragment
* [X] Use ViewPager to navigate across steps
* [X] Implement Adaptative UI for Tablets.
* [X] Application has a companion homescreen widget. 
* [X] Widget displays ingredient list for desired recipe.
* [ ] Complete View Model implementation, including using of Data Binding for the card views.
* [ ] Write Esspresso Tests to test aspects of the UI.
