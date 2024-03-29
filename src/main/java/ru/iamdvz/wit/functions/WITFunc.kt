package ru.iamdvz.wit.functions

import org.bukkit.entity.Entity
import ru.iamdvz.wit.WIT
import ru.iamdvz.wit.utils.MythicMobUtil
import ru.iamdvz.wit.utils.Utils.makeBar
import java.util.*

object WITFunc {
    private var stringFormat = WIT.getInstance().config.getString("format-of-mythic-mobs-string")!!
    private var showLvl = WIT.getInstance().config.getBoolean("show-level-of-mythic-mobs")
    private var showHealth = WIT.getInstance().config.getBoolean("show-health-of-mythic-mobs")
    private var emptyChar = WIT.getInstance().config.getString("mythic-mob-health-bar-empty")!!
    private var fullChar = WIT.getInstance().config.getString("mythic-mob-health-bar-full")!!
    var nothingString = WIT.getInstance().config.getString("nothing-string")!!
    private var mobAndItString = WIT.getInstance().config.getConfigurationSection("mob-and-it-parameters")!!
    var checkerDistance = WIT.getInstance().config.getDouble("checker-distance")

    @JvmStatic
    fun WITReload() {
        stringFormat = WIT.getInstance().config.getString("format-of-mythic-mobs-string")!!
        showLvl = WIT.getInstance().config.getBoolean("show-level-of-mythic-mobs")
        showHealth = WIT.getInstance().config.getBoolean("show-health-of-mythic-mobs")
        emptyChar = WIT.getInstance().config.getString("mythic-mob-health-bar-empty")!!
        fullChar = WIT.getInstance().config.getString("mythic-mob-health-bar-full")!!
        nothingString = WIT.getInstance().config.getString("nothing-string")!!
        mobAndItString = WIT.getInstance().config.getConfigurationSection("mob-and-it-parameters")!!
        checkerDistance = WIT.getInstance().config.getDouble("checker-distance")
    }

    @JvmStatic
    fun outputFunction(entity: Entity?): String {
        if (!MythicMobUtil.isMythicMob(entity)) {
            return nothingString
        }
        var s = MythicMobUtil.getMythicMobDisplayName(entity)?.let { stringFormat.replace("{name}", it) }
        if (showLvl) s = s?.replace("{level}", MythicMobUtil.getMythicMobLevel(entity).toString())
        if (showHealth) s = s?.replace("{health}", MythicMobUtil.getMythicMobCurrentHealth(entity).toString())
        return s?.replace("{level}", "")?.replace("{health}", "") ?: "NULL"
    }

    @JvmStatic
    fun healthBarFunc(entity: Entity?, size: Int): String {
        return if (!MythicMobUtil.isMythicMob(entity)) { nothingString
        } else makeBar(emptyChar, fullChar, size, MythicMobUtil.getMythicMobMaxHealth(entity), MythicMobUtil.getMythicMobCurrentHealth(entity))
    }

    @JvmStatic
    fun getParameter(entity: Entity?, parameter: String): String? {
        if (!MythicMobUtil.isMythicMob(entity)) {
            return nothingString
        }
        return if (mobAndItString.contains(MythicMobUtil.getMythicMobConfigName(entity))) {
            mobAndItString.getString(MythicMobUtil.getMythicMobConfigName(entity) + "." + parameter)
        } else nothingString
    }
}