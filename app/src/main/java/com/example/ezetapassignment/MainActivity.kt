package com.example.ezetapassignment

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.core.widget.TextViewCompat
import coil.compose.AsyncImage
import com.example.ezetapassignment.ui.theme.EzetapAssignmentTheme
import com.example.mynetworklibrary.ApiClient
import com.example.mynetworklibrary.CustomUi
import com.example.mynetworklibrary.NetworkServiceInterface
import com.example.mynetworklibrary.Uidata
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private var data: CustomUi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.extras?.get("CUSTOM_UI") as CustomUi
        setContent {
            EzetapAssignmentTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        data?.let { SetContentView(it) }
                    }
                }
            }
        }
    }


    @Composable
    private fun SetContentView(data: CustomUi) {
        val modifier = Modifier
            .fillMaxWidth()
            .padding(19.dp, 0.dp, 19.dp, 0.dp)
        val uidata: List<Uidata> = data.uidata
        Column {
            AsyncImage(
                model = data.logo_url,
                contentDescription = null,
                modifier = modifier.padding(0.dp, 28.dp, 0.dp, 10.dp)
            )
            Text(
                text = data.heading_text,
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                modifier = modifier.padding(0.dp, 22.dp, 0.dp, 0.dp)
            )
            for (f in uidata) {
                when (f.uitype) {

                    "edittext" -> {

                        var text by rememberSaveable { mutableStateOf("") }
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text(f.hint) },
                            modifier = modifier
                        )
                    }
                    "label" -> {
                        Text(
                            text = f.value,
                            modifier = modifier.padding(0.dp, 28.dp, 0.dp, 10.dp)
                        )
                    }
                    "button" -> {
                        Button(
                            onClick = { },
                            modifier = modifier
                                .padding(0.dp, 28.dp, 0.dp, 10.dp)
                        ) {
                            Text(text = f.value, modifier = Modifier.padding( 10.dp))
                        }

                    }
                }


                if (f.uitype == "edittext") {

                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        EzetapAssignmentTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {


                }
            }
        }
    }
}



