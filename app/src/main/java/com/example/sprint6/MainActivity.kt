package com.example.sprint6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/* Lista para una aplicación que utiliza información de una API y que contiene imágenes.
[ ] Permiso de internet y clear traffic (o algo así) en Manifest
[x] ViewBinding
[x] Dependencias: Coil, Room, Retrofit, navigation, viewModel y Test de android. Agregar el plugin de ROOM y cambiar la versión de la 1.8 a la 17
[ ] Crear package data, data.local, data.remote y presentation(o vistas)
[ ] Remoto:
    [ ] Dataclass/ POJO
    [ ] API (interfaz)
    [ ] Cliente de Retrofit
[ ] Repositorio
[ ] Local:
    [ ] Entity
    [ ] DAO
    [ ] Database
[ ] View Model
[ ] 2 pantallas: Listado y detalle
[ ] Navigation
[ ] Listado: RecyclerView + ViewHolder + Adapter
[ ] Detalle: RecyclerView + ViewHolder + Adapter
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}