package net.minecraft.network.protocol.minecraft_47.play;

import net.minecraft.network.protocol.minecraft_47.play.client.*;
import net.minecraft.network.protocol.minecraft_47.play.server.*;
import net.minecraft.network.protocol.AProtocol;

public class ProtocolPlay47 extends AProtocol {
	public ProtocolPlay47() {
		super(0);
		this.registerPacket(false, S00PacketKeepAlive.class, S00PacketKeepAlive::new);
		this.registerPacket(false, S01PacketJoinGame.class, S01PacketJoinGame::new);
		this.registerPacket(false, S02PacketChat.class, S02PacketChat::new);
		this.registerPacket(false, S03PacketTimeUpdate.class, S03PacketTimeUpdate::new);
		this.registerPacket(false, S04PacketEntityEquipment.class, S04PacketEntityEquipment::new);
		this.registerPacket(false, S05PacketSpawnPosition.class, S05PacketSpawnPosition::new);
		this.registerPacket(false, S06PacketUpdateHealth.class, S06PacketUpdateHealth::new);
		this.registerPacket(false, S07PacketRespawn.class, S07PacketRespawn::new);
		this.registerPacket(false, S08PacketPlayerPosLook.class, S08PacketPlayerPosLook::new);
		this.registerPacket(false, S09PacketHeldItemChange.class, S09PacketHeldItemChange::new);
		this.registerPacket(false, S0APacketUseBed.class, S0APacketUseBed::new);
		this.registerPacket(false, S0BPacketAnimation.class, S0BPacketAnimation::new);
		this.registerPacket(false, S0CPacketSpawnPlayer.class, S0CPacketSpawnPlayer::new);
		this.registerPacket(false, S0DPacketCollectItem.class, S0DPacketCollectItem::new);
		this.registerPacket(false, S0EPacketSpawnObject.class, S0EPacketSpawnObject::new);
		this.registerPacket(false, S0FPacketSpawnMob.class, S0FPacketSpawnMob::new);
		this.registerPacket(false, S10PacketSpawnPainting.class, S10PacketSpawnPainting::new);
		this.registerPacket(false, S11PacketSpawnExperienceOrb.class, S11PacketSpawnExperienceOrb::new);
		this.registerPacket(false, S12PacketEntityVelocity.class, S12PacketEntityVelocity::new);
		this.registerPacket(false, S13PacketDestroyEntities.class, S13PacketDestroyEntities::new);
		this.registerPacket(false, S14PacketEntity.class, S14PacketEntity::new);
		this.registerPacket(false, S14PacketEntity.S15PacketEntityRelMove.class, S14PacketEntity.S15PacketEntityRelMove::new);
		this.registerPacket(false, S14PacketEntity.S16PacketEntityLook.class, S14PacketEntity.S16PacketEntityLook::new);
		this.registerPacket(false, S14PacketEntity.S17PacketEntityLookMove.class, S14PacketEntity.S17PacketEntityLookMove::new);
		this.registerPacket(false, S18PacketEntityTeleport.class, S18PacketEntityTeleport::new);
		this.registerPacket(false, S19PacketEntityHeadLook.class, S19PacketEntityHeadLook::new);
		this.registerPacket(false, S19PacketEntityStatus.class, S19PacketEntityStatus::new);
		this.registerPacket(false, S1BPacketEntityAttach.class, S1BPacketEntityAttach::new); // ToDo: Кастомные пакеты
		this.registerPacket(false, S1CPacketEntityMetadata.class, S1CPacketEntityMetadata::new);
		this.registerPacket(false, S1DPacketEntityEffect.class, S1DPacketEntityEffect::new);
		this.registerPacket(false, S1EPacketRemoveEntityEffect.class, S1EPacketRemoveEntityEffect::new);
		this.registerPacket(false, S1FPacketSetExperience.class, S1FPacketSetExperience::new);
		this.registerPacket(false, S20PacketEntityProperties.class, S20PacketEntityProperties::new);
		this.registerPacket(false, S21PacketChunkData.class, S21PacketChunkData::new);
		this.registerPacket(false, S22PacketMultiBlockChange.class, S22PacketMultiBlockChange::new);
		this.registerPacket(false, S23PacketBlockChange.class, S23PacketBlockChange::new);
		this.registerPacket(false, S24PacketBlockAction.class, S24PacketBlockAction::new);
		this.registerPacket(false, S25PacketBlockBreakAnim.class, S25PacketBlockBreakAnim::new);
		this.registerPacket(false, S26PacketMapChunkBulk.class, S26PacketMapChunkBulk::new);
		this.registerPacket(false, S27PacketExplosion.class, S27PacketExplosion::new);
		this.registerPacket(false, S28PacketEffect.class, S28PacketEffect::new);
		this.registerPacket(false, S29PacketSoundEffect.class, S29PacketSoundEffect::new);
		this.registerPacket(false, S2APacketParticles.class, S2APacketParticles::new);
		this.registerPacket(false, S2BPacketChangeGameState.class, S2BPacketChangeGameState::new);
		this.registerPacket(false, S2CPacketSpawnGlobalEntity.class, S2CPacketSpawnGlobalEntity::new);
		this.registerPacket(false, S2DPacketOpenWindow.class, S2DPacketOpenWindow::new);
		this.registerPacket(false, S2EPacketCloseWindow.class, S2EPacketCloseWindow::new);
		this.registerPacket(false, S2FPacketSetSlot.class, S2FPacketSetSlot::new);
		this.registerPacket(false, S30PacketWindowItems.class, S30PacketWindowItems::new);
		this.registerPacket(false, S31PacketWindowProperty.class, S31PacketWindowProperty::new);
		this.registerPacket(false, S32PacketConfirmTransaction.class, S32PacketConfirmTransaction::new);
		this.registerPacket(false, S33PacketUpdateSign.class, S33PacketUpdateSign::new);
		this.registerPacket(false, S34PacketMaps.class, S34PacketMaps::new);
		this.registerPacket(false, S35PacketUpdateTileEntity.class, S35PacketUpdateTileEntity::new);
		this.registerPacket(false, S36PacketSignEditorOpen.class, S36PacketSignEditorOpen::new);
		this.registerPacket(false, S37PacketStatistics.class, S37PacketStatistics::new);
		this.registerPacket(false, S38PacketPlayerListItem.class, S38PacketPlayerListItem::new);
		this.registerPacket(false, S39PacketPlayerAbilities.class, S39PacketPlayerAbilities::new);
		this.registerPacket(false, S3APacketTabComplete.class, S3APacketTabComplete::new);
		this.registerPacket(false, S3BPacketScoreboardObjective.class, S3BPacketScoreboardObjective::new);
		this.registerPacket(false, S3CPacketUpdateScore.class, S3CPacketUpdateScore::new);
		this.registerPacket(false, S3DPacketDisplayScoreboard.class, S3DPacketDisplayScoreboard::new);
		this.registerPacket(false, S3EPacketTeams.class, S3EPacketTeams::new);
		this.registerPacket(false, S3FPacketCustomPayload.class, S3FPacketCustomPayload::new);
		this.registerPacket(false, S40PacketDisconnect.class, S40PacketDisconnect::new);
		this.registerPacket(false, S41PacketServerDifficulty.class, S41PacketServerDifficulty::new);
		this.registerPacket(false, S42PacketCombatEvent.class, S42PacketCombatEvent::new);
		this.registerPacket(false, S43PacketCamera.class, S43PacketCamera::new);
		this.registerPacket(false, S44PacketWorldBorder.class, S44PacketWorldBorder::new);
		this.registerPacket(false, S45PacketTitle.class, S45PacketTitle::new);
		this.registerPacket(false, S46PacketSetCompressionLevel.class, S46PacketSetCompressionLevel::new);
		this.registerPacket(false, S47PacketPlayerListHeaderFooter.class, S47PacketPlayerListHeaderFooter::new);
		this.registerPacket(false, S48PacketResourcePackSend.class, S48PacketResourcePackSend::new);
		this.registerPacket(false, S49PacketUpdateEntityNBT.class, S49PacketUpdateEntityNBT::new);

		this.registerPacket(true, C00PacketKeepAlive.class, C00PacketKeepAlive::new);
		this.registerPacket(true, C01PacketChatMessage.class, C01PacketChatMessage::new);
		this.registerPacket(true, C02PacketUseEntity.class, C02PacketUseEntity::new);
		this.registerPacket(true, C03PacketPlayer.class, C03PacketPlayer::new);
		this.registerPacket(true, C03PacketPlayer.C04PacketPlayerPosition.class, C03PacketPlayer.C04PacketPlayerPosition::new);
		this.registerPacket(true, C03PacketPlayer.C05PacketPlayerLook.class, C03PacketPlayer.C05PacketPlayerLook::new);
		this.registerPacket(true, C03PacketPlayer.C06PacketPlayerPosLook.class, C03PacketPlayer.C06PacketPlayerPosLook::new);
		this.registerPacket(true, C07PacketPlayerDigging.class, C07PacketPlayerDigging::new);
		this.registerPacket(true, C08PacketPlayerBlockPlacement.class, C08PacketPlayerBlockPlacement::new);
		this.registerPacket(true, C09PacketHeldItemChange.class, C09PacketHeldItemChange::new);
		this.registerPacket(true, C0APacketAnimation.class, C0APacketAnimation::new);
		this.registerPacket(true, C0BPacketEntityAction.class, C0BPacketEntityAction::new);
		this.registerPacket(true, C0CPacketInput.class, C0CPacketInput::new);
		this.registerPacket(true, C0DPacketCloseWindow.class, C0DPacketCloseWindow::new);
		this.registerPacket(true, C0EPacketClickWindow.class, C0EPacketClickWindow::new);
		this.registerPacket(true, C0FPacketConfirmTransaction.class, C0FPacketConfirmTransaction::new);
		this.registerPacket(true, C10PacketCreativeInventoryAction.class, C10PacketCreativeInventoryAction::new);
		this.registerPacket(true, C11PacketEnchantItem.class, C11PacketEnchantItem::new);
		this.registerPacket(true, C12PacketUpdateSign.class, C12PacketUpdateSign::new);
		this.registerPacket(true, C13PacketPlayerAbilities.class, C13PacketPlayerAbilities::new);
		this.registerPacket(true, C14PacketTabComplete.class, C14PacketTabComplete::new);
		this.registerPacket(true, C15PacketClientSettings.class, C15PacketClientSettings::new);
		this.registerPacket(true, C16PacketClientStatus.class, C16PacketClientStatus::new);
		this.registerPacket(true, C17PacketCustomPayload.class, C17PacketCustomPayload::new);
		this.registerPacket(true, C18PacketSpectate.class, C18PacketSpectate::new);
		this.registerPacket(true, C19PacketResourcePackStatus.class, C19PacketResourcePackStatus::new);
	}
}
