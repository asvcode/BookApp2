package com.example.android.bookapp2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * URL for Google data from the Google dataset
     */
    private static final String GOOGLE_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=isbn:0735619670";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an {@link AsyncTask} to perform the HTTP request to the given URL
        // on a background thread. When the result is received on the main UI thread,
        // then update the UI.
        BookAsyncTask task = new BookAsyncTask();
        task.execute(GOOGLE_REQUEST_URL);
    }

        /**
         * Update the UI with the given earthquake information.
         */
    private void updateUi(Book books) {
        TextView authorTextView = (TextView) findViewById(R.id.author_name);
        authorTextView.setText(books.authors);

        TextView bookNameTextView = (TextView) findViewById(R.id.book_name);
        bookNameTextView.setText((books.title));
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class BookAsyncTask extends AsyncTask<String, Void, Book> {

        /**
         * This method is invoked (or called) on a background thread, so we can perform
         * long-running operations like making a network request.
         *
         * It is NOT okay to update the UI from a background thread, so we just return an
         * {@link Book} object as the result.
         */
        protected Book doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            Book result = Utils.fetchBookData(urls[0]);
            return result;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         *
         * It IS okay to modify the UI within this method. We take the {@link Book} object
         * (which was returned from the doInBackground() method) and update the views on the screen.
         */
        protected void onPostExecute(Book result) {
            // If there is no result, do nothing.
            if (result == null) {
                return;
            }

            updateUi(result);
        }
    }
}
