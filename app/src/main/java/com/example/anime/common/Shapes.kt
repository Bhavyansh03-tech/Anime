package com.example.anime.common

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize

@Composable
fun ClippedImage(shapeImageId: Int, contentImageId: Int) {
    val context = LocalContext.current
    val shapeBitmap = BitmapFactory.decodeStream(context.resources.openRawResource(shapeImageId))
    val contentBitmap = BitmapFactory.decodeStream(context.resources.openRawResource(contentImageId))

    val shapeImageBitmap = shapeBitmap.asImageBitmap()
    val contentImageBitmap = contentBitmap.asImageBitmap()

    Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f)) {

        drawIntoCanvas { canvas ->
            canvas.withSaveLayer(Rect(0f, 0f, size.width, size.height), Paint()) {
                // Draw the shape image as a mask
                drawImage(
                    image = shapeImageBitmap,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt())
                )

                // Clip the content image within the shape
                drawImage(
                    image = contentImageBitmap,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                    blendMode = BlendMode.SrcIn
                )
            }
        }
    }
}