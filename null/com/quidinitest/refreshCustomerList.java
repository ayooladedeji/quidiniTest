
                                    package com.quidinitest;

                                    import android.os.AsyncTask;

                                    // The types specify: 1. input data type (String)
                                    // 2. progress type (Integer)
                                    // 3. result type (String)

                                    public class refreshCustomerList extends AsyncTask<String, Integer, String> {
                                    protected void onPreExecute() {
                                    // Executed in UIThread
                                    }

                                    protected String doInBackground(String... strings) {
                                    String result = "42";
                                    return result;
                                    }

                                    protected void onProgressUpdate(Integer... values) {
                                    // Used to update progress indicator
                                    }

                                    protected void onPostExecute(String result) {
                                    // Executed in UIThread
                                    }
                                    }
                                