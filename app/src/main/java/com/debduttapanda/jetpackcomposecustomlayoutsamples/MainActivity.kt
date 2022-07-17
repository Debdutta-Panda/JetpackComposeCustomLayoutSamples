package com.debduttapanda.jetpackcomposecustomlayoutsamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.debduttapanda.jetpackcomposecustomlayoutsamples.ui.theme.JetpackComposeCustomLayoutSamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCustomLayoutSamplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(){
                        AlternateStairFlowLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .background(Color.Red)
                        ) {
                            repeat(30){
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(50.dp,25.dp)
                                        .background(Color.Green)
                                ){
                                    Text(it.toString())
                                }
                            }
                        }
                        AlternateStairFlowLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(500.dp)
                                .background(Color.Blue)
                        ) {
                            repeat(30){
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(50.dp,25.dp)
                                        .background(Color.Green)
                                ){
                                    Text(it.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlternateStairFlowLayout(//our own flow layout, Great!
    modifier: Modifier = Modifier,
    spacing: Int = 0,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier,
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var direction = 1
            var x = 0
            var y = 0
            placeables.forEach {placeable->
                if(direction>0&&x+placeable.width>constraints.maxWidth){
                    direction *= -1
                }
                else if(direction<0&&x-placeable.width<0){
                    direction *= -1
                }
                placeable.place(x,y)
                x += placeable.width*direction
                y += placeable.height + spacing
            }
        }
    }
}

@Composable
fun StairFlowLayout(//our own flow layout, Great!
    modifier: Modifier = Modifier,
    spacing: Int = 20,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier,
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var x = 0
            var y = 0
            placeables.forEach {placeable->
                if(x+placeable.width>constraints.maxWidth){
                    x = 0
                }
                placeable.place(x,y)
                x += placeable.width
                y += placeable.height + spacing
            }
        }
    }
}

@Composable
fun FlowLayout(//our own flow layout, Great!
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier,
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var x = 0
            var y = 0
            var maxY = 0
            placeables.forEach {placeable->
                if(x+placeable.width>constraints.maxWidth){
                    x = 0
                    y += maxY
                    maxY = 0
                }
                placeable.place(x,y)
                x += placeable.width
                if(maxY<placeable.height){
                    maxY = placeable.height
                }
            }
        }
    }
}

@Composable
fun RowClipLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier,
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var x = 0
            placeables.forEach {placeable->
                if(x+placeable.width>constraints.maxWidth){
                    return@forEach
                }
                placeable.place(x,0)
                x += placeable.width//seems good
            }
        }
    }
}

@Composable
fun GoodRowLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier
            .padding(48.dp),
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var x = 0
            placeables.forEach {placeable->
                placeable.place(x,0)
                x += placeable.width//seems good
            }
        }
    }
}

@Composable
fun GoodColumnLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier
            .padding(48.dp),
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val looseConstraints = constraints.copy(
                minWidth = 0,
                minHeight = 0
            )
            val placeables = measurable.map {measurable ->
                measurable.measure(looseConstraints)
            }
            var y = 0
            placeables.forEach {placeable->
                placeable.place(0,y)
                y += placeable.height//seems good
            }
        }
    }
}

@Composable
fun ColumnLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier
            .padding(48.dp),
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val placeables = measurable.map {measurable ->
                measurable.measure(constraints)//actually we are applying parent constraints on child
            }
            var y = 0
            placeables.forEach {placeable->
                placeable.place(0,y)
                y += placeable.height//the height seems wrong
            }
        }
    }
}

@Composable
fun DoSomethingLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier
            .padding(48.dp),
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val placeables = measurable.map {measurable ->
                measurable.measure(constraints)
            }
            placeables.forEach {placeable->
                placeable.place(0,0)
            }
        }
    }
}

@Composable
fun DoNothingLayout(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Layout(//the basic building block for custom layout
        modifier = modifier,
        content = content
    ){measurable, constraints->
        layout(constraints.maxWidth, constraints.maxHeight){
            val placeables = measurable.map {measurable ->
                measurable.measure(constraints)
            }
            placeables.forEach {placeable->
                placeable.place(0,0)
            }
        }
    }
}