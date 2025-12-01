package com.sample.bookpedia.feat_book.presentation.book_details.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_cover
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import cmp_bookpedia.composeapp.generated.resources.go_back
import cmp_bookpedia.composeapp.generated.resources.mark_as_favorite
import cmp_bookpedia.composeapp.generated.resources.remove_from_favorites
import coil3.compose.rememberAsyncImagePainter
import com.sample.bookpedia.core.presentation.DesertWhite
import com.sample.bookpedia.core.presentation.SandYellow
import com.sample.bookpedia.core.presentation.animations.PulseAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BlurredImageBackground(
    imageUrl: String?,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = imageUrl, onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if (size.width > 1 && size.height > 1) Result.success(it.painter)
            else Result.failure(Exception("Invalid Image Dimensions"))
        })

    Box {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
                    .background(DesertWhite)
            ) {
                imageLoadResult?.getOrNull()?.let { painter ->
                    Image(
                        painter = painter,
                        contentDescription = stringResource(Res.string.book_cover),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .blur(20.dp)
                            .drawWithContent {
                                drawContent()
                                val maskColors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.10f),
                                    Color.Black.copy(alpha = 0.25f),
                                    Color.Black.copy(alpha = 0.50f),
                                    Color.Black.copy(alpha = 0.75f),
                                    Color.Black
                                )
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = maskColors,
                                        startY = size.height * 0.35f,
                                        endY = size.height * 0.7f
                                    ),
                                    blendMode = BlendMode.DstOut
                                )
                                // ▼▼ Horizontal Side Fade ▼▼
                                val horizontalMaskColors = listOf(
                                    Color.Black,                           // left side fade
                                    Color.Black.copy(alpha = 0.40f),
                                    Color.Black.copy(alpha = 0.15f),
                                    Color.Transparent,                     // center fully visible
                                    Color.Black.copy(alpha = 0.15f),
                                    Color.Black.copy(alpha = 0.40f),
                                    Color.Black                            // right side fade
                                )

                                drawRect(
                                    brush = Brush.horizontalGradient(
                                        colors = horizontalMaskColors,
                                        startX = 0f,
                                        endX = size.width
                                    )
                                )
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(0.7f).background(DesertWhite))
        }
        IconButton(
            onClick = onBackClicked,
            modifier = Modifier.align(Alignment.TopStart).padding(top = 48.dp, start = 16.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(Res.string.go_back),
                tint = Color.White
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            Box {
                ElevatedCard(
                    modifier = Modifier.height(200.dp).aspectRatio(2 / 3f),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = Color.Transparent
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 12.dp
                    )
                ) {
                    AnimatedContent(
                        targetState = imageLoadResult,
                        transitionSpec = {
                            slideInVertically() + fadeIn(animationSpec = tween(3000)) togetherWith
                                    slideOutVertically() + fadeOut(animationSpec = tween(3000))
                        }
                    ) { result ->
                        when (result)  {
                            null -> Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                PulseAnimation(modifier = Modifier.size(60.dp))
                            }
                            else -> Image(
                                painter = if (result.isSuccess) painter else {
                                    painterResource(Res.drawable.book_error_2)
                                },
                                contentDescription = stringResource(Res.string.book_cover),
                                modifier = Modifier.fillMaxSize().background(Color.Transparent),
                                contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit
                            )
                        }
                    }
                }
                IconButton(
                    onClick = onFavouriteClicked,
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(SandYellow, Color.Transparent),
                                radius = 70f,
                            )
                        )
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Star else Icons.Outlined.Email,
                        contentDescription = stringResource(if (isFavourite) Res.string.remove_from_favorites else Res.string.mark_as_favorite),
                        tint = Color.Red
                    )
                }
            }
            content()
        }
    }
}