package com.camelloncase.testedeperformance03.ui.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance01.R
import com.camelloncase.testedeperformance03.database.Survey

class RecipesRecyclerViewAdapter(
    private val context: Context,
    private val surveyClickDeleteInterface: SurveyClickDeleteInterface,
    private val surveyClickInterface: SurveyClickInterface
) : RecyclerView.Adapter<RecipesRecyclerViewAdapter.ViewHolder>() {

    private val allSurveys = ArrayList<Survey>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val businessName: TextView = itemView.findViewById(R.id.businessNameTextView)
        val neighborhoodName: TextView = itemView.findViewById(R.id.neighborhoodNameTextView)
        val createDate: TextView = itemView.findViewById(R.id.createDateTextView)
        val deleteSurvey: ImageView = itemView.findViewById(R.id.deleteImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(context).inflate(
            R.layout.survey_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.businessName.text = allSurveys[position].businessName
        holder.neighborhoodName.text = allSurveys[position].neighborhoodName

        val formattedSurveyDate = "Created: ${allSurveys[position].createDate}"
        holder.createDate.text = formattedSurveyDate

        holder.deleteSurvey.setOnClickListener {
            surveyClickDeleteInterface.onDeleteIconClick(allSurveys[position])
        }

        holder.itemView.setOnClickListener {
            surveyClickInterface.onSurveyClick(allSurveys[position])
        }
    }

    override fun getItemCount(): Int {
        return allSurveys.size
    }

    fun updateList(newList: List<Survey>) {
        allSurveys.clear()
        allSurveys.addAll(newList)
        notifyDataSetChanged()
    }
}

interface SurveyClickDeleteInterface {
    fun onDeleteIconClick(survey: Survey)
}

interface SurveyClickInterface {
    fun onSurveyClick(survey: Survey)
}
