package com.huskydreaming.medievalcharactercards;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.handlers.interfaces.CommandHandler;
import com.huskydreaming.medievalcharactercards.commands.BaseCommand;
import com.huskydreaming.medievalcharactercards.commands.subcommands.*;
import com.huskydreaming.medievalcharactercards.handlers.implementations.ConfigHandlerImpl;
import com.huskydreaming.medievalcharactercards.handlers.implementations.DependencyHandlerImpl;
import com.huskydreaming.medievalcharactercards.handlers.implementations.LocalizationHandlerImpl;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.DependencyHandler;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.LocalizationHandler;
import com.huskydreaming.medievalcharactercards.listeners.PlayerListener;
import com.huskydreaming.medievalcharactercards.repositories.implementations.CharacterRepositoryImpl;
import com.huskydreaming.medievalcharactercards.repositories.implementations.GenderRepositoryImpl;
import com.huskydreaming.medievalcharactercards.repositories.implementations.TitleRepositoryImpl;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.GenderRepository;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.TitleRepository;

public final class MedievalCharacterCardsPlugin extends HuskyPlugin {

    @Override
    public void onInitialize() {
        handlerRegistry.register(ConfigHandler.class, new ConfigHandlerImpl());
        handlerRegistry.register(LocalizationHandler.class, new LocalizationHandlerImpl());
        handlerRegistry.register(DependencyHandler.class, new DependencyHandlerImpl());

        repositoryRegistry.register(CharacterRepository.class, new CharacterRepositoryImpl());
        repositoryRegistry.register(GenderRepository.class, new GenderRepositoryImpl());
        repositoryRegistry.register(TitleRepository.class, new TitleRepositoryImpl());
    }

    @Override
    public void onPostInitialize() {
        CommandHandler commandHandler = handlerRegistry.provide(CommandHandler.class);
        commandHandler.setCommandExecutor(new BaseCommand(this));
        commandHandler.add(new AgeCommand(this));
        commandHandler.add(new DescriptionCommand(this));
        commandHandler.add(new FirstCommand(this));
        commandHandler.add(new GenderCommand(this));
        commandHandler.add(new HeightCommand(this));
        commandHandler.add(new LastCommand(this));
        commandHandler.add(new MiddleCommand(this));
        commandHandler.add(new TitleCommand(this));
        commandHandler.add(new ViewCommand(this));

        registerListeners(new PlayerListener(this));
    }
}