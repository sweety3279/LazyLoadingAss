package motobeans.architecture.retrofit.response

/**
 * Created by swati on 24/9/2018.
 */
object Response {

    data class ResponseData(var title:String, var rows:ArrayList<RowData>)
    data class  RowData(var title:String,var description:String, var imageHref:String)

}
