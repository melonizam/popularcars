package com.techneekfactory.popularcars.popularcars;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techneekfactory.popularcars.popularcars.extras.Faq;
import com.techneekfactory.popularcars.popularcars.extras.QuestionsModel;
import com.techneekfactory.popularcars.popularcars.extras.URLEndpoints;
import com.techneekfactory.popularcars.popularcars.extras.Vehicles;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link GeneralLoanFragment#} factory method to
 * create an instance of this fragment.
 */
public class GeneralLoanFragment extends Fragment {


    private ArrayList<String> questions;
    private ArrayList<String> answers;

    public String[] ques;
    public String[] ans;

    public ArrayList<QuestionsModel> questionsModels;

    public  ListView listView;

    public RecyclerView rvItems;

    public QuestionAnswerAdapter adapter;
    public WebView webView;


    public GeneralLoanFragment() {
        // Required empty public constructor
    }

    ProgressDialog webLoadingProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_general_loan, container, false);



//        questions = new ArrayList<>();
//        answers = new ArrayList<>();
//        questionsModels = new ArrayList<QuestionsModel>();
//
//
//        rvItems = (RecyclerView) v.findViewById(R.id.rvItems);
//
//        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvItems.setItemAnimator(new DefaultItemAnimator());

//        Collections.addAll(allData, Calculator);
        webView = (WebView) v.findViewById(R.id.webview);

        webLoadingProgressBar = new ProgressDialog(getActivity());
        webLoadingProgressBar.setIndeterminate(true);
        webLoadingProgressBar.setCancelable(false);
        webLoadingProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        webLoadingProgressBar.setMessage("Loading...");

        webLoadingProgressBar.show();


        //Get the Featured Cars
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://popularcarsoman.dev.techneek.in/appgateway/endPoint.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        webLoadingProgressBar.dismiss();

                        webView.loadData("<body>"+response+"</body>", "text/html; charset=utf-8", "UTF-8");


                        System.out.println("webview: " + response);
                        String status, message, customerID, fullName, mobileNumber, password, language, mobileNumberVerified, active;
//
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            status = obj.getString("status");
//                            message = obj.getString("message");
//
//                            Log.d("JSON Reply", obj.toString());
//
//                            if (status.equals("success")) {
//
//                                JSONObject dataObj = obj.getJSONObject("data");
//
//
//                                JSONArray jsonDataArray = dataObj.getJSONArray("faqs");
//
//                                for (int i = 0; i < jsonDataArray.length(); i++) {
//                                    JSONObject data = jsonDataArray.getJSONObject(i);
//
////                                    Faq faqs =
////                                            new Faq(data.getString("question"), data.getString("answer"));
////
////
////                                    faqLists.add(faqs);
////                                    faqLists.notify();
//                                    String s = data.getString("question");
//                                    s = s.replace("<p>", "");
//                                    String r = data.getString("answer");
//                                    r = r.replace("<p>", "");
//                                    r = r.replace("<b>"," ");
//                                    r = r.replace("</p>", "");
//                                    r = r.replace("</b>"," ");
//                                    r = r.replace("<ul>", "");
//                                    r = r.replace("</ul>"," ");
//                                    r = r.replace("<li>", "");
//                                    r = r.replace("</li>"," ");
//
//                                            QuestionsModel questionsModel = new QuestionsModel(s,r );
//                                    questionsModels.add(questionsModel);
//
//                                    questions.add(data.getString("question"));
//                                    answers.add(data.getString("answer"));
//
//
//                                    System.out.println("Question: " +data.getString("question")+ "  Answer: "+ data.getString("answer"));
//                                }
//
//
////                                ques = new String[questions.size()];
////                                questions.toArray(ques);
////                                ans = new String[answers.size()];
////                                answers.toArray(ans);
//
//
//
//                                Log.d("Array" , questionsModels.get(0).getAnswer()+ questionsModels.get(1).getQuestion());
//
//
//                              QuestionAnswerAdapter adapter = new QuestionAnswerAdapter(getContext(),questionsModels);
//                                rvItems.setAdapter(adapter);
//
////
////                                listView.setAdapter(adapter);
//
//
////                                adapter.notifyDataSetChanged();
//                                System.out.println("ques2" + ques[2] + "Answ4: " + ans[3] );
//
//
////                                String[] faq_array = new String[faqLists.size()];
////                                faqLists.toArray(faq_array);
////                                textView.setText(String.valueOf( faq_array[1]));
////                                System.out.println("Array: ");
//
//
//
////                                Log.d("FaqList",  " -- " + faqLists.size());
////                                customerID = data.getString("customerID");
//
//
////                                textView.setText(response.toString());
//                            } else {
//                                System.out.println("Could not");
////                            }
//
//                        } catch (Throwable tx) {
//                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
//                        }

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                String userMobileNumber = mobileText.getText().toString();
//                String userPassword = passwordText.getText().toString();

                params.put("action", "GENERALFAQ");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);



        return v;
    }

}