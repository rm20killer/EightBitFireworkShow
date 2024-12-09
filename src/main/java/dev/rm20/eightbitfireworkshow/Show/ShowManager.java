package dev.rm20.eightbitfireworkshow.Show;

import dev.rm20.eightbitfireworkshow.EightBitFireworkShow;
import org.bukkit.Bukkit;

import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;
public class ShowManager {
    static EightBitFireworkShow FireworkPlugin;
    public ShowManager(EightBitFireworkShow FireworkPlugin)
    {
        this.FireworkPlugin = FireworkPlugin;
    }


    public static void startShow(String showName) {
        // 1. Load the show file (e.g., "shows/Grand Finale.yml")
        File showFile = new File(FireworkPlugin.getDataFolder(), "shows/" + showName + ".yml");
        if (!showFile.exists()) {
            Bukkit.getLogger().warning("Show file not found: " + showFile.getPath());
            return;
        }

        // 2. Parse the YAML file (use a library like SnakeYAML)
        Map<String, Object> showData;
        showData = (Map<String, Object>) YamlConfiguration.loadConfiguration(showFile);

        // 3. Extract show data
        List<Map<?, ?>> frames = (List<Map<?, ?>>) showData.get("frames");

        // 4. Schedule the firework show
        new BukkitRunnable() {
            int currentFrame = 0;

            @Override
            public void run() {
                if (currentFrame >= frames.size()) {
                    this.cancel(); // End of show
                    return;
                }

                Map<?, ?> frameData = frames.get(currentFrame);
                int frameNumber = (int) frameData.get("frame");

                // Ensure we're on the correct frame (in case of server lag)
                if (currentFrame == frameNumber) {
                    playFrame(frameData);
                    currentFrame++;
                }
            }
        }.runTaskTimer(FireworkPlugin, 0L, 1L); // Run every tick (20 ticks per second)
    }

    private static void playFrame(Map<?, ?> frameData) {
        // Play fireworks
        List<Map<?, ?>> fireworksData = (List<Map<?, ?>>) frameData.get("fireworks");
        if (fireworksData != null) {
            for (Map<?, ?> fireworkData : fireworksData) {
                spawnFirework(fireworkData);
            }
        }

        // Play particles
        List<Map<?, ?>> particlesData = (List<Map<?, ?>>) frameData.get("particles");
        if (particlesData != null) {
            for (Map<?, ?> particleData : particlesData) {
                spawnParticles(particleData);
            }
        }

        // Play lasers
        List<Map<?, ?>> lasersData = (List<Map<?, ?>>) frameData.get("lasers");
        if (lasersData != null) {
            for (Map<?, ?> laserData : lasersData) {
                spawnLaser(laserData);
            }
        }
    }

    private static void spawnFirework(Map<?, ?> fireworkData) {
        // ... (Extract firework data from fireworkData map)
        // ... (Create Firework entity, set its FireworkMeta, and launch it)
    }

    private static void spawnParticles(Map<?, ?> particleData) {
        // ... (Extract particle data from particleData map)
        // ... (Use world.spawnParticle() to spawn particles)
    }

    private static void spawnLaser(Map<?, ?> laserData) {
        // ... (Extract laser data from laserData map)
        // ... (Spawn and manipulate Guardian entities to create laser beams)
    }

}
