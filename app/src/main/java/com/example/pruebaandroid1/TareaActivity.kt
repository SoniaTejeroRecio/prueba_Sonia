package com.example.pruebaandroid1

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebaandroid1.databinding.ActivityTaskBinding
import com.example.pruebaandroid1.models.Tarea
import com.example.pruebaandroid1.repositories.TareaRepositorioImpl

class TareaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private val repositorio = TareaRepositorioImpl()
    private lateinit var adaptadorPendientes: ArrayAdapter<Tarea>
    private lateinit var adaptadorHechas: ArrayAdapter<Tarea>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //Llamada a super.onCreate para que se ejecute el método original
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adaptadorPendientes = ArrayAdapter(this, android.R.layout.simple_list_item_1, repositorio.obtenerTareasPendientes())
        adaptadorHechas = ArrayAdapter(this, android.R.layout.simple_list_item_1, repositorio.obtenerTareasCompletadas())


        binding.listViewTareas.adapter = adaptadorPendientes
        registerForContextMenu(binding.listViewTareas)

        //Configurar el botón para agregar nuevas tareas
        binding.btnAgregarTarea.setOnClickListener {
            val descripcion = binding.etTareaInput.text.toString()
            if (descripcion.isNotBlank()) {
                repositorio.agregarTarea(Tarea(descripcion))
                actualizarVista()
                binding.etTareaInput.text.clear() // Limpiar el campo de entrada
            }
        }

        //Configurar el botón para ver tareas pendientes
        binding.btnVerPendientes.setOnClickListener {
            binding.listViewTareas.adapter = adaptadorPendientes
        }

        //Configurar el botón para ver tareas completadas
        binding.btnVerHechas.setOnClickListener {
            binding.listViewTareas.adapter = adaptadorHechas
        }
    }

    //Método para actualizar la vista de las listas
    private fun actualizarVista() {
        adaptadorPendientes.notifyDataSetChanged()
        adaptadorHechas.notifyDataSetChanged()
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    //Manejar las acciones del menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.menu_eliminar -> {
                repositorio.eliminarTarea(info.position)
                actualizarVista()
                true
            }
            R.id.menu_marcar_hecha -> {
                repositorio.marcarTareaComoCompleta(info.position)
                actualizarVista()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
