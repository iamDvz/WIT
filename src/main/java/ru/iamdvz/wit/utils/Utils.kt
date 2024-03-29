package ru.iamdvz.wit.utils

import org.bukkit.FluidCollisionMode
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.util.RayTraceResult
import ru.iamdvz.wit.functions.WITFunc

import java.lang.Math.round
import kotlin.math.roundToInt

object Utils {
    @JvmStatic
    fun makeBar(empty: String, full: String, size: Int, max: Double, current: Double): String {
        return (full.repeat((size * current / max).roundToInt()) + empty.repeat((size * (1 - current / max)).roundToInt()))
    }

    @JvmStatic
    fun getRayTraceResult(player: Player): RayTraceResult? {
        return player.world.rayTrace(player.eyeLocation.add(player.location.direction), player.eyeLocation.direction, WITFunc.checkerDistance, FluidCollisionMode.NEVER, false, 1.0) { entity: Entity -> entity.uniqueId != player.uniqueId }
    }
}