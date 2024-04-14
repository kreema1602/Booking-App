package com.example.bookingapp.pages

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingapp.R
import com.example.bookingapp.core.compose.HistoryCard
import com.example.bookingapp.core.ui.theme.OrangePrimary

val mavenProFamily = FontFamily(
    Font(R.font.maven_pro_regular, FontWeight.Normal),
    Font(R.font.maven_pro_bold, FontWeight.Bold),
    Font(R.font.maven_pro_medium, FontWeight.Medium),
    Font(R.font.maven_pro_semibold, FontWeight.SemiBold)
)

@Composable
fun BarChart(data: List<Float>, notes: List<String>, modifier: Modifier = Modifier) {
    val maxDataValue = data.maxOrNull() ?: 0f

    Canvas(modifier = modifier.fillMaxSize()) {
        drawIntoCanvas {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val barWidth = canvasWidth / (data.size * 2)

            data.forEachIndexed { index, value ->
                val barHeight = (value / maxDataValue) * canvasHeight
                val startX = index * (barWidth + barWidth / 2)
                val startY = canvasHeight - barHeight
                val endX = startX + barWidth
                val endY = canvasHeight

                val barRect = Offset(startX, startY) to Offset(endX, endY)
                it.drawRect(
                    barRect.first.x,
                    barRect.first.y,
                    barRect.second.x,
                    barRect.second.y,
                    Paint().apply {
                        color = OrangePrimary
                    })


                val textToDraw = notes.getOrNull(index) ?: ""
            }
        }
    }
}


@Composable
fun LineChart(data: List<Float>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        drawIntoCanvas {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val dataPoints = data.mapIndexed { index, value ->
                val x = (index / (data.size - 1).toFloat()) * canvasWidth
                val y = (1 - value) * canvasHeight
                Offset(x, y)
            }

            dataPoints.forEachIndexed { index, point ->
                if (index < dataPoints.size - 1) {
                    val nextPoint = dataPoints[index + 1]
                    it.drawLine(
                        point,
                        nextPoint,
                        Paint().apply {
                            color = OrangePrimary
                            strokeWidth = 4.dp.toPx()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChartScreen() {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Financial Charts",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )

//            Text(
//                text = "Bar Chart",
//                fontFamily = mavenProFamily,
//                modifier = Modifier.padding(16.dp),
//                fontSize = 24.sp,
//            )

//            BarChart(
//                data = listOf(100f, 200f, 150f, 300f, 250f), modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                notes = listOf("Jan", "Feb", "Mar", "Apr", "May")
//            )

            Text(
                text = "Line Chart",
                fontFamily = mavenProFamily,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp,
            )

            LineChart(
                data = listOf(0.2f, 0.5f, 0.3f, 0.8f, 0.6f, 0.9f), modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "History",
                fontFamily = mavenProFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp)
            )
            for (i in 0..5) {
                HistoryCard(
                    from = "2021-10-01",
                    to = "2021-10-02",
                    customerName = "John Doe",
                    roomName = "Room 101"
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun BarChartPreview() {
    ChartScreen()
}
