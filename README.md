# Consommation d'une API REST avec Retrofit dans une application Android "Consommation Api Image"

Ce projet Android utilise **Retrofit** pour consommer une API REST et afficher une liste de photos dans un **RecyclerView**.

##

## 📜 Explication des lignes de code clés

### 1️⃣ Définition de l'interface de l'API : `ApiService.java`

```java
@GET("photos")
Call<List<Photo>> getPhotos();
```

📌 Cette ligne définit une requête HTTP GET pour récupérer une liste de photos depuis l'API.

### 2️⃣ Configuration de Retrofit et appel de l'API : `MainActivity.java`

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();
```

📌 Initialise Retrofit avec l'URL de base de l'API et un convertisseur JSON.

```java
ApiService apiService = retrofit.create(ApiService.class);
Call<List<Photo>> call = apiService.getPhotos();
```

📌 Création d'une instance du service API et appel de la requête pour récupérer les photos.

```java
call.enqueue(new Callback<List<Photo>>() {
    @Override
    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
        if (response.isSuccessful() && response.body() != null) {
            photosAdapter = new PhotosAdapter(response.body());
            recyclerView.setAdapter(photosAdapter);
        } else {
            showToast("Erreur de chargement");
        }
    }

    @Override
    public void onFailure(Call<List<Photo>> call, Throwable t) {
        showToast("Erreur : " + t.getMessage());
    }
});
```

📌 Envoi de la requête asynchrone avec `enqueue()`, gestion de la réponse en cas de succès ou d'échec.

### 3️⃣ Affichage des photos dans un `RecyclerView` : `PhotosAdapter.java`

```java
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {
    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.titleTextView.setText(photo.getTitle());
        holder.urlTextView.setText(photo.getUrl());
    }
}
```

📌 `PhotosAdapter` adapte les données récupérées et les affiche dans la liste.

##

---

📧 \*\*Amine Ouhiba 4gl2 \*\*

