Assumptions Made

    The application assumes a stable internet connection for fetching anime data from the Jikan API.

    Each anime entry from the API has a valid anime_id and relevant details like title, episodes, rating, genres, synopsis, and trailer.

    The trailer field may not always have a valid URL, so fallback mechanisms (e.g., displaying the poster image) are implemented.

    The user interacts with the app through an intuitive UI with a RecyclerView for the list..
  
    Basic error handling is provided for cases like null data or API failure.


Features Implemented

  1. Anime List Page (Home Screen)

    Displays a list of top-rated anime fetched from the Jikan API endpoint: https://api.jikan.moe/v4/top/anime.

    Title, anime image, episode count, rating

  2. Anime Detail Page 

    When an anime is selected, its details are displayed.

    Title, Plot/Synopsis, Genres, Number of episodes, Rating
    
    A trailer player using WebView to play the trailer if available, or the poster image as a fallback.
    
    Data is fetched from the Jikan API endpoint: https://api.jikan.moe/v4/anime/{anime_id}.

3. Tech Stack Used
    1. Kotlin
    2. RecyclerView of fetching items in list
    3. Retrofit for network call
    4. Picasso for url to image
    5. WebView of url to video
