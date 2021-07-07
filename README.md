# Project name: 
TheBirthdaysApp

# Description: 
The app uses Retrofit to fetch a JSON from an API. List of names and birthdays are shown in a RecyclerView in the startup screen. When an entry is tapped on, it loads a details screen with more details about the selected item. The app uses Livedata and attempts to MVVM architecture pattern to a good extent. It also use the Navigation Library.

# Installation:
Clone the repository and run the app on an emulator. 

# Usage:
Has been tested on a Pixel 3 XL(API 29). Make sure the emulator can access the internet. When the App is loaded, it should display a list of items after fetching some data from the internet. Click on an item to show details about the user in a different screen.

<h2>Screenshots</h2>
<div>
<img src="../documentation_assets/DocumentationAssets/1.png?raw=true" width="200px">
<img src="../documentation_assets/DocumentationAssets/2.png?raw=true" width="200px">
<img src="../documentation_assets/DocumentationAssets/3.png?raw=true" width="200px">
<img src="../documentation_assets/DocumentationAssets/4.png?raw=true" width="200px">
<img src="../documentation_assets/DocumentationAssets/5.png?raw=true" width="200px">
<img src="../documentation_assets/DocumentationAssets/6.png?raw=true" width="200px">
</div>

<h2>Some improvements oustanding</h2>
<ul>  
 <li>Make sure the repository is injected using Dagger-Hilt. I.e. dependency injection</li>
 <li>Implement DiffUtil</li>
 <li>Use strings.xml rather than hardcoding.</li>
 <li>Get the date of birth to appear in the device users time zone. At the moment all dates appear in UTC.</li>
 <li>Improve the look and feel of the app.</li>
 <li>Remove the backing field for live data and use a livedata builder instead</li>
</ul>

# Contributing:
No contributions are expected or necessary. 

# Credits: 
Built as part of a coding challenge. I've been told that no need to give any credits. So, not including anything here.

# License: 
Educational use only.
