# MiniProjet - The Moovie App

## Liste des membres
Camille PERES

## Fonctionnalités:
Le mini-projet permet de consulter les films selon une catégorie sélectionnée. Dans la vue du détail du films, il est possible de voir le cast, les trailers, les visualiser, de voir les films similaires et de mettre les films en favoris. De ce fait, une page de consultation des favoris est disponible. Enfin, il est possible de voir les dernières sorties de films. D'autre part, il est également possible de consulter les avis laissés sur un film.

- 9 fragments:
  - Fragment de catégorie: affiche les différentes catégories de films
  - Fragment de films : affiche les films relatifs à la catégorie sélectionnée
  - Fragment de détails de films : affiche les détails du film, le casting, les films similaires, les trailers, mise en favoris, les reviews
  - Fragment de films similaires: affiche les films similaires au film sélectionné
  - Fragment de visualisation de vidéo: permet de visualiser la vidéo une fois cliquée dessus
  - Fragment des votes: affiche les reviews faites sur les films
  - Fragment des dernières sorties: affiche les films dernièrement sortis et classés par popularité
  - Fragment de favoris : affiche les films mis en favoris à partir du détail du film
  - Fragment à propos : explique le projet

## Navigation:

![navigation](/assets/imgs/NavigationView.png)

## Contenu des fragments
- Fragment About:
  - Consultation du profil
  
![about](/assets/imgs/AboutView.png)


- Fragment Category:
  - Visualisation des catégories de films
  
![category](/assets/imgs/CategoryView.png)


- Fragment DetailsMovie:
  - Détails du film : titre, cast, trailers, description, durée du film, langue, mettre en favoris, films similaires
  
![details](/assets/imgs/DetailsMovieView.png)


- Fragment FavoriteMovie:
  - Visualisation du film mis en favoris
  
![favorite](/assets/imgs/FavoriteView.png)

- Fragment LastRelease:
  - Visualisation des dernières sorties triées par poupularité
  
![release](/assets/imgs/LastReleaseView.png)


- Fragment Movie:
  - Visualisation des films
  
![movie](/assets/imgs/MovieViews.png)


- Fragment Reviews:
  - Visualisation des reviews attribuées au film
  
![review](/assets/imgs/ReviewsView.png)


- Fragment SimilarMovie:
  - Visualisation des films similaires au film sélectionné
  
![similar](/assets/imgs/SimilarMovieView.png)


- Fragment Ytb:
  - Visualisation des vidéos
  
![videos](/assets/imgs/YtVieww.png)
  

## Traduction
  - Anglais
  - Français

## Scroll Infini

## Librairies
- Navigation-fragment
- Hilt : Injection de dépendances
- Gson : Sérialisation et Désérialisation JSON
- Retrofit: Pour consommer l'API The Moovie DB
- Glide : Pour afficher les images
- OkHttp: Client HTTP
- AndroidYtbPlayer: Pour lire les vidéos



