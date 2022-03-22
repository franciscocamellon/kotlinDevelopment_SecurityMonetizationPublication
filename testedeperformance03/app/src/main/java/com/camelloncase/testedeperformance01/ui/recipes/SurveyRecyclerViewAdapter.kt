package com.camelloncase.testedeperformance01.ui.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance01.R
import com.camelloncase.testedeperformance01.database.Survey

class RecipesRecyclerViewAdapter(
    private val context: Context,
    private val noteClickDeleteInterface: NoteClickDeleteInterface,
    private val noteClickInterface: NoteClickInterface
) : RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Survey>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipeNameText)
        val recipeDate: TextView = itemView.findViewById(R.id.recipeCreateDateText)
        val recipeStyle: TextView = itemView.findViewById(R.id.recipeStyleText)
        val deleteRecipe: ImageView = itemView.findViewById(R.id.deleteRecipeImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(
            R.layout.recipe_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.recipeName.text = allNotes[position].recipeName
        holder.recipeStyle.text = allNotes[position].recipeStyle

        val formattedRecipeDate = "Created: ${allNotes[position].recipeCreateDate}"
        holder.recipeDate.text = formattedRecipeDate

        holder.deleteRecipe.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Survey>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(survey: Survey)
}

interface NoteClickInterface {
    fun onNoteClick(survey: Survey)
}
