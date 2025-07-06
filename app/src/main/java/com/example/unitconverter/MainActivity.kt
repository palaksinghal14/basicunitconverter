package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitconverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun App() {

      var inputvalue by remember{ mutableStateOf("") }
      var outputvalue by remember{ mutableStateOf("") }
      var iexpanded by remember{ mutableStateOf(false) }
      var oexpanded by remember{ mutableStateOf(false) }
      var inputunit by remember{ mutableStateOf("meters") }
      var outputunit by remember{ mutableStateOf("meters") }
      var conversionfactor =remember{ mutableStateOf(1.0) }  // how much 1 input unit is in metres
      var oconversionfactor =remember{ mutableStateOf(1.0) }  // how much 1 output unit is in metres

     fun convertunits(){
         var InputvalueDouble = inputvalue.toDoubleOrNull() ?: 0.0 // means it will return the value if not null otherwise will return 0.0
         val rawResult = InputvalueDouble * conversionfactor.value / oconversionfactor.value  // simply converting the value
         val result = (rawResult * 100).roundToInt() / 100.0 // making it in two decimal places
         outputvalue= result.toString()
     }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "UNIT CONVERTER ",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedTextField(
            value = inputvalue,
            onValueChange = {
                inputvalue=it
                convertunits()},
            label = { Text(text = "Enter Value") },
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.padding(16.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // INPUT BOX
            Box {
                   // INPUT BUTTON
                  Button(onClick = { iexpanded=true }) {
                    Text(text = inputunit)

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                  }

                  DropdownMenu(expanded = iexpanded,
                      onDismissRequest = { iexpanded=false }
                  ) {
                    DropdownMenuItem(text = { Text(text = "metres") },
                        onClick = {
                            iexpanded=false
                            inputunit="metres"
                            conversionfactor.value= 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "centimetres") },
                        onClick = {
                            iexpanded=false
                            inputunit="centimetres"
                            conversionfactor.value= 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "feet") },
                        onClick = {
                            iexpanded=false
                            inputunit="feet"
                            conversionfactor.value= 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "millimetres") },
                        onClick = {
                            iexpanded=false
                            inputunit="millimetres"
                            conversionfactor.value= 0.001
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "kilometres") },
                        onClick = {
                            iexpanded=false
                            inputunit="kilometres"
                            conversionfactor.value= 1000.0
                            convertunits()
                        }
                    )
                 }
            }

            Spacer(modifier = Modifier.padding(16.dp))

                // OUTPUT BOX
            Box {
                // OUTPUT BUTTON
                Button(onClick = { oexpanded=true }) {
                    Text(text = outputunit)

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(expanded = oexpanded,
                    onDismissRequest = { oexpanded=false }
                ) {
                    DropdownMenuItem(text = { Text(text = "metres") },
                        onClick = {
                            oexpanded=false
                            outputunit="metres"
                            oconversionfactor.value= 1.0
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "centimetres") },
                        onClick = {
                            oexpanded=false
                            outputunit="centimetres"
                            oconversionfactor.value= 0.01
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "feet") },
                        onClick = {
                            oexpanded=false
                            outputunit="feet"
                            oconversionfactor.value= 0.3048
                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "millimetres") },
                        onClick = {
                            oexpanded=false
                            outputunit="millimetres"
                            oconversionfactor.value= 0.001
                            convertunits() }
                    )
                    DropdownMenuItem(text = { Text(text = "kilometres") },
                        onClick = {
                            oexpanded=false
                            outputunit="kilometres"
                            oconversionfactor.value= 1000.0
                            convertunits()
                        }
                    )

                }
            }

        }

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Result : $outputvalue  $outputunit",
                fontSize= 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
    }
}