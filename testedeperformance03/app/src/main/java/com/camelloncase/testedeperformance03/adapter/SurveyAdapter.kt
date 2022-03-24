package com.camelloncase.testedeperformance03.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.testedeperformance03.R
import com.camelloncase.testedeperformance03.database.Survey

class SurveyAdapter(
    private val context: Context,
    private val surveyClickDeleteInterface: SurveyClickDeleteInterface,
    private val surveyClickInterface: SurveyClickInterface
) : RecyclerView.Adapter<SurveyAdapter.ViewHolder>() {

    private val allSurveys = ArrayList<Survey>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val business: TextView = itemView.findViewById(R.id.businessTextView)
        val neighborhood: TextView = itemView.findViewById(R.id.neighborhoodTextView)
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

        holder.business.text = allSurveys[position].businessName
        holder.neighborhood.text = allSurveys[position].neighborhoodName

        val formattedRecipeDate = "Created: ${allSurveys[position].createDate}"
        holder.createDate.text = formattedRecipeDate

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
