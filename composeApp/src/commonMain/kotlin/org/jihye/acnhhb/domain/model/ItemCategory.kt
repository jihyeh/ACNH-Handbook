package org.jihye.acnhhb.domain.model

import acnhhandbook.composeapp.generated.resources.Res
import acnhhandbook.composeapp.generated.resources.material_type_bamboo
import acnhhandbook.composeapp.generated.resources.material_type_bush
import acnhhandbook.composeapp.generated.resources.material_type_egg
import acnhhandbook.composeapp.generated.resources.material_type_feather
import acnhhandbook.composeapp.generated.resources.material_type_fence
import acnhhandbook.composeapp.generated.resources.material_type_flower
import acnhhandbook.composeapp.generated.resources.material_type_fruit
import acnhhandbook.composeapp.generated.resources.material_type_ingredient
import acnhhandbook.composeapp.generated.resources.material_type_mushroom
import acnhhandbook.composeapp.generated.resources.material_type_none
import acnhhandbook.composeapp.generated.resources.material_type_ore
import acnhhandbook.composeapp.generated.resources.material_type_other
import acnhhandbook.composeapp.generated.resources.material_type_plant
import acnhhandbook.composeapp.generated.resources.material_type_shell
import acnhhandbook.composeapp.generated.resources.material_type_snowflake
import acnhhandbook.composeapp.generated.resources.material_type_star_fragment
import acnhhandbook.composeapp.generated.resources.material_type_trash
import acnhhandbook.composeapp.generated.resources.material_type_tree
import acnhhandbook.composeapp.generated.resources.material_type_wood
import org.jetbrains.compose.resources.StringResource

enum class ItemCategory(
    val stringRes: StringResource,
    val dbValue: String? = null,
) {
    NONE(
        stringRes = Res.string.material_type_none,
    ),
    BAMBOO(
        stringRes = Res.string.material_type_bamboo,
        dbValue = "Bamboo"
    ),
    EGG(
        stringRes = Res.string.material_type_egg,
        dbValue = "Egg"
    ),
    SNOWFLAKE(
        stringRes = Res.string.material_type_snowflake,
        dbValue = "Snowflake"
    ),
    STAR_FRAGMENT(
        stringRes = Res.string.material_type_star_fragment,
        dbValue = "Star Fragment"
    ),
    WOOD(
        stringRes = Res.string.material_type_wood,
        dbValue = "Wood"
    ),
    SHELL(
        stringRes = Res.string.material_type_shell,
        dbValue = "Shell"
    ),
    FEATHER(
        stringRes = Res.string.material_type_feather,
        dbValue = "Feather"
    ),
    FRUIT(
        stringRes = Res.string.material_type_fruit,
        dbValue = "Fruit"
    ),
    FLOWER(
        stringRes = Res.string.material_type_flower,
        dbValue = "Flower"
    ),
    PLANT(
        stringRes = Res.string.material_type_plant,
        dbValue = "Plant"
    ),
    TREE(
        stringRes = Res.string.material_type_tree,
        dbValue = "Tree"
    ),
    BUSH(
        stringRes = Res.string.material_type_bush,
        dbValue = "Bush"
    ),
    MUSHROOM(
        stringRes = Res.string.material_type_mushroom,
        dbValue = "Mushroom"
    ),
    INGREDIENT(
        stringRes = Res.string.material_type_ingredient,
        dbValue = "Ingredient"
    ),
    ORE(
        stringRes = Res.string.material_type_ore,
        dbValue = "Ore"
    ),
    FENCE(
        stringRes = Res.string.material_type_fence,
    ),
    TRASH(
        stringRes = Res.string.material_type_trash,
        dbValue = "Trash"
    ),
    OTHER(
        stringRes = Res.string.material_type_other,
    )
}

