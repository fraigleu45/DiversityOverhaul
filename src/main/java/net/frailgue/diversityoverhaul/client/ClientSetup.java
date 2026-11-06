package net.frailgue.diversityoverhaul.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranim.api.PlayerAnimationAccess;
import com.zigythebird.playeranim.api.PlayerAnimationFactory;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonConfiguration;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonMode;
import com.zigythebird.playeranimcore.enums.PlayState;
// Ces deux imports sont à vérifier avec Alt+Entrée si nécessaire
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ClientSetup {

    public static final ResourceLocation ANIMATION_LAYER_ID = ResourceLocation.fromNamespaceAndPath("diversityoverhaul", "main_layer");

    public static final KeyMapping SAW_KEY = new KeyMapping(
            "key.diversityoverhaul.saw",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.misc"
    );

    @EventBusSubscriber(modid = "diversityoverhaul", value = Dist.CLIENT)
    public static class ModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                    ANIMATION_LAYER_ID,
                    1500,
                    player -> {
                        PlayerAnimationController controller = new PlayerAnimationController(player,
                                (ctrl, state, animSetter) -> PlayState.STOP
                        );

                        // Configuration première personne
                        controller.setFirstPersonMode(FirstPersonMode.THIRD_PERSON_MODEL);
                        controller.setFirstPersonConfiguration(new FirstPersonConfiguration()
                                .setShowRightArm(true)
                                .setShowLeftArm(false)
                                .setShowArmor(true)
                        );
                        controller.setFirstPersonFollowsCamera(true);

                        return controller;
                    }
            ));
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(SAW_KEY);
        }
    }

    @EventBusSubscriber(modid = "diversityoverhaul", value = Dist.CLIENT)
    public static class GameEvents {

        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            if (Minecraft.getInstance().player == null) return;

            while (SAW_KEY.consumeClick()) {
                PlayerAnimationController controller = (PlayerAnimationController) PlayerAnimationAccess.getPlayerAnimationLayer(
                        Minecraft.getInstance().player, ANIMATION_LAYER_ID);

                if (controller != null) {
                    ResourceLocation sawId = ResourceLocation.fromNamespaceAndPath("diversityoverhaul", "saw");
                    boolean success = controller.triggerAnimation(sawId);

                    if (success) {
                        System.out.println("Animation SAW lancée !");
                    } else {
                        System.out.println("ERREUR : Animation SAW introuvable (vérifiez le fichier JSON)");
                    }
                }
            }
        }
    }
}